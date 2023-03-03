package nus.iss.ssfbatch2exam.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import nus.iss.ssfbatch2exam.models.Address;
import nus.iss.ssfbatch2exam.models.Quotation;
import nus.iss.ssfbatch2exam.models.Stock;
import nus.iss.ssfbatch2exam.repository.CartRepository;
import nus.iss.ssfbatch2exam.services.QuotationService;

@Controller
// @RequestMapping(path = "")
public class PurchaseOrderController {
    @Autowired
    CartRepository cartRepo;

    @Autowired
    QuotationService quotationSvc;

    // landing page , new page and reset all...
    @GetMapping(path = { "/", "/index.html" })
    public String getview1(Model model) {
        model.addAttribute("stock", new Stock());
        cartRepo.clearstock();
        List<Stock> cart = new LinkedList<Stock>();
        model.addAttribute("cart", cart);
        return "view1";
    }

    @GetMapping(path = { "/cart" })
    public String getviewsession(Model model) {

        model.addAttribute("stock", new Stock());

        List<Stock> cart = new LinkedList<Stock>();
        Map<String, Stock> cartMap = cartRepo.findAll();
        for (String i : cartMap.keySet()) {
            cart.add(cartMap.get(i));
        }

        model.addAttribute("cart", cart);
        return "view1";
    }

    // adding to cart
    @PostMapping(path = "/cart")
    public String addcart(@Valid Stock stock, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Customer added invalid item");

            List<Stock> cart = new LinkedList<Stock>();
            Map<String, Stock> cartMap = cartRepo.findAll();
            for (String i : cartMap.keySet()) {
                cart.add(cartMap.get(i));
            }
            model.addAttribute("cart", cart);
            return "view1";
        }

        cartRepo.addstock(stock);
        model.addAttribute("stock", new Stock());
        List<Stock> cart = new LinkedList<Stock>();
        Map<String, Stock> cartMap = cartRepo.findAll();
        for (String i : cartMap.keySet()) {
            cart.add(cartMap.get(i));
        }
        model.addAttribute("cart", cart);
        return "view1";
    }

    @GetMapping(path = "/shippingaddress")
    public String addressinput(Model model) {

        if (cartRepo.checkcartsize() == 0) {
            return "redirect:/cart";
        } else {
            model.addAttribute("address", new Address());
            return "view2";
        }
    }

    @PostMapping(path = "/invoice")
    public String postaddress(@Valid Address address, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "view2";
        }

        // Get Quotation using Quotation Service....
        Quotation quotation = quotationSvc.getQuotations();

        // Start Calculation
        // quotation.getQuotation("apple");

        Float total = 0f;

        Map<String, Stock> cartMap = cartRepo.findAll();
        for (String i : cartMap.keySet()) {
            String item = cartMap.get(i).getItemname();
            Float quantity = Float.parseFloat(cartMap.get(i).getQuantity().toString()) ;
            Float itemprice = quotation.getQuotation(item);
            total = total + (quantity*itemprice);
        }

        model.addAttribute("invoiceid", quotation.getQuoteId());
        model.addAttribute("address", address);
        model.addAttribute("total", String.format("%.2f",total));

        System.out.println("Invoice Generated #################");
        cartRepo.clearstock();
        System.out.println("Customer Cart Cleared #################");

        return "view3";
    }

}
