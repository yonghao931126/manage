package com.haojie.manage.controller;

import com.haojie.manage.domain.Product;
import com.haojie.manage.service.IProductService;
import com.haojie.manage.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    /**
     * 会将字符串转换成日期对象  这种解决方法一般是spring3.1版本之前使用的
      * @param //binder
     */
   /* @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateStringEditor());
    }*/


    @RequestMapping("/findAll.do")
    /*@RolesAllowed("ADMIN")*/
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        //配合jsp页面中的参数
        List<Product> productList = productService.findAll();
        mv.addObject("productList", productList);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Product product) {
        productService.save(product);
        return "redirect:findAll.do";
    }


}
