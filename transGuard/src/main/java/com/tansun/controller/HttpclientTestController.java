package com.tansun.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author linhb
 * @create 2019-08-14
 */
@RestController
@RequestMapping("httpclient")
public class HttpclientTestController {

    @RequestMapping(value = "/setcookie", method = RequestMethod.GET)
    public void setCookie(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("1");

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void testGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("1");

    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void testPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("2");

    }
}
