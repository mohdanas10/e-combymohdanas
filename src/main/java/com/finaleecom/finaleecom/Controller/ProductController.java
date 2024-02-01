package com.finaleecom.finaleecom.Controller;


import com.finaleecom.finaleecom.Model.Amount;
import com.finaleecom.finaleecom.Model.Cart;
import com.finaleecom.finaleecom.Model.Product;
import com.finaleecom.finaleecom.Model.User;
import com.finaleecom.finaleecom.Services.AmountServices;
import com.finaleecom.finaleecom.Services.CartServices;
import com.finaleecom.finaleecom.Services.ProductService;
import com.finaleecom.finaleecom.Services.UserServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/home")
public class ProductController {
    List<Product> productList = new ArrayList<>();
    Random random = new Random();
    String uniqueEmail ;

    String productId ="";

    @Autowired
    private ProductService productService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private CartServices cartServices;

    @Autowired
    private AmountServices amountServices;
    @GetMapping("/login")
    public String showLoginPage(Model model){
        User product = new User();
        System.out.println(uniqueEmail);
        System.out.println(uniqueEmail);
        model.addAttribute("product", product);
        return "login";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create/add")
    public String checkCreate( @ModelAttribute Product product,Model model){
//        int randomNumber = random.nextInt();
//        product.setProductId(randomNumber);

//        List<Product> obj= productList;
        UUID id = UUID.randomUUID();
        System.out.println(id);
        product.setEmail(uniqueEmail);
        System.out.println(uniqueEmail);
        System.out.println(product);
        this.productService.saveProduct(product);
//        List<Product> obj = this.productService.findAllProduct();
        List<Product> obj = this.productService.findProductByEmail(uniqueEmail);
        System.out.println(obj);
        model.addAttribute("objs", obj);
        System.out.println(productList);
        return "/showProduct";
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id, Model model){
//        for(Product p : productList){
//            if(p.getProductId() == id){
//                productList.remove(p);
//                break;
//            }
//        }
//        List<Product> products = this.productService.findAllProduct();
//        for(Product p : products){
//            if(p.getProductId() == id){
//                this.productService.deleteById();
//            }
//        }
        this.productService.deleteById(id);
        List<Product> obj= this.productService.findProductByEmail(uniqueEmail);
        model.addAttribute("objs", obj);
        return "/showProduct";
    }


    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable String id ,  Model model){
//        Product list = null;
//        for(Product p: productList){
//            if(p.getProductId() == id){
//                list = p;
//                break;
//            }
//        }
        Product list = this.productService.findProductById(id);
        productId = list.getProductId();
        model.addAttribute("product", list);
        return "/updateProduct";
    }

    @PostMapping("/update")
    public  String checkUpdate(@ModelAttribute Product product, Model model){
//        List<Product> obj= productList;
//        for(Product pt : productList){
//            if(pt.getProductId() == productId){
//                pt.setPrice(product.getPrice());
//                pt.setName(product.getName());
//                pt.setType(product.getType());
//                break;
//            }
//        }
        Product pt  = this.productService.findProductById(productId);
        System.out.println(pt+" "+product);

            pt.setPrice(product.getPrice());
            pt.setName(product.getName());
            pt.setType(product.getType());
            this.productService.saveProduct(pt);
            System.out.println(pt);

        List<Product> obj = this.productService.findProductByEmail(uniqueEmail);
        model.addAttribute("objs", obj);
        return "/showProduct";
    }

    @PostMapping("/checklogin")
    public String checklogin(@ModelAttribute User user1, Model model){

        System.out.println(productList);
        User user = this.userServices.findByUserName(user1.getUserName());
        if(user == null ){
            User users = new User();
            model.addAttribute("users" , users);
            return "/signupPage";
        }
        else if(user.getUserPassword().equals(user1.getUserPassword())){
            uniqueEmail = user.getUserName();
            List<Product> obj = this.productService.findProductByEmail(uniqueEmail);
            System.out.println(uniqueEmail);
            model.addAttribute("objs", obj);
            return "/showProduct";
        }
        return "/error";
    }

    @PostMapping("/search")
    public String searchProduct( @PathVariable String name  ){
        List<Product> searchedProduct = this.productService.searchedProductResutl(name);
        System.out.println(name);
        return "/showProduct";
    }

    @GetMapping("/getuserbyid")
    public String getUserById(@PathVariable String id ){
        Product pt =  this.productService.findProductById(id);
        return null;
    }

    @GetMapping("/getProductByType")
    public String getProductByType(@PathVariable String type ){
        List<Product> productList1 = this.productService.getProductByType(type);
        return null;
    }


    @GetMapping("/getProductByPrice")
    public String getProductByPrice(@PathVariable int price){
        List<Product> productList1 = this.productService.getProductByPrice(price);
        return null;
    }

    @PostMapping("/signUP")
    public String signUpPage(@ModelAttribute User user , Model model){
        User userByUserName = this.userServices.findByUserName(user.getUserName());
        if(userByUserName == null){
            uniqueEmail = user.getUserName();
            this.userServices.save(user);
            model.addAttribute("product" , new User());
            return "/login";
        }

        return "/userExit";
    }

    @GetMapping("/dosignup")
    public String dosignup(Model model){
        User users = new User();
        model.addAttribute("users", users);
        return "/signupPage";
    }

    @GetMapping("/cart/{id}")
    public String  addToCart(@PathVariable String id , Model model){
        Product product = this.productService.findProductById(id);
        Cart c = new Cart();
        c.setCartId(product.getProductId());
        c.setType(product.getType());
        c.setName(product.getName());
        c.setPrice(product.getPrice());
        c.setEmail(uniqueEmail);
        this.cartServices.saveCart(c);
        Amount amount = this.amountServices.findByEmail(uniqueEmail);
        System.out.println(amount);
        if(amount == null){
            Amount amount1 = new Amount();
            amount1.setEmail(uniqueEmail);
            amount1.setTotalAmount(product.getPrice());
            this.amountServices.saveAmount(amount1);
        }
        else {
           amount.setTotalAmount(this.amountServices.findByEmail(uniqueEmail).getTotalAmount() + product.getPrice());
           this.amountServices.saveAmount(amount);
        }
        System.out.println(this.amountServices.findByEmail(uniqueEmail));
        List<Product> obj = this.productService.findProductByEmail(uniqueEmail);
        model.addAttribute("objs", obj);
        return "/showProduct";
    }


    @GetMapping("/showCart")
    public String showCart(Model model ){
        List<Cart> obj = this.cartServices.findCartByEmail(uniqueEmail);
        Amount amount1 = this.amountServices.findByEmail(uniqueEmail);
        if(amount1 == null){
            Amount amount = new Amount();
            amount.setEmail(uniqueEmail);
            amount.setTotalAmount(0);
            this.amountServices.saveAmount(amount);
            model.addAttribute("amount" , amount);
            model.addAttribute("objs", obj);
            return "/cartPage";
        }
        else {
            Amount amount  = this.amountServices.findByEmail(uniqueEmail);
            model.addAttribute("amount" , amount);
            model.addAttribute("objs", obj);
            return "/cartPage";
        }

    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCartItems(@PathVariable String  id ,  Model model){
        int price = this.productService.findProductById(id).getPrice();
        Amount amountObj = this.amountServices.findByEmail(this.productService.findProductById(id).getEmail());
        amountObj.setTotalAmount(amountObj.getTotalAmount() - price);
        this.amountServices.saveAmount(amountObj);
        this.cartServices.deleteCartById(id);
        List<Cart> obj = this.cartServices.findCartByEmail(uniqueEmail);
        Amount amount = this.amountServices.findByEmail(this.productService.findProductById(id).getEmail());
        System.out.println(amount);
        model.addAttribute("objs", obj);
        model.addAttribute("amount", amount);
        return "/cartPage";
    }


    @GetMapping("/backToProduct")
    public String backtoproduct(Model model){
        List<Product> obj = this.productService.findProductByEmail(uniqueEmail);
        model.addAttribute("objs", obj);
        return "/showProduct";
    }

    @GetMapping("/forgotPass")
    public String forgotPassword(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "/forgotPassword";
    }

    @PostMapping("/forgotPassCheck")
    public String checkForgot(@ModelAttribute User user, Model model){
        User user1 = this.userServices.findByUserName(user.getUserName());
        System.out.println(user1.getUserName());
        if(user1 == null){
            return "/error";
        }
        else {
            user1.setUserPassword(user.getUserPassword());
            this.userServices.save(user1);
            User product = new User();
            model.addAttribute("product", product);
            return "login";
        }
    }

    @GetMapping("/searchProudct")
    public String searchProudctByName(@RequestParam  String inputData,  Model model) {
        System.out.println(inputData);
        List<Product> obj = this.productService.searchedProductResutl(inputData);
        System.out.println(obj);
        model.addAttribute("objs", obj);
        return "/showProduct";
    }


}
