package com.hc.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.springboot.bean.Vip;
import com.hc.springboot.result.R;
import com.hc.springboot.service.VipService;
import com.hc.springboot.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VipController {
    @Autowired
    private VipService vipService;
//    @GetMapping("/list/{page}")
//    public PageInfo<Vip> list(@PathVariable int page){
//        //设置当前页码和每页显示的条数
//        PageHelper.startPage(page, Constant.PAGE_SIZE);
//        //查询数据
//        List<Vip> vips = vipService.findAll();
//        //封装数据到PageInfo对象当中
//        PageInfo<Vip> vipPageInfo = new PageInfo<>(vips);
//        return vipPageInfo;
//    }
@GetMapping("/list/{page}")
public R <PageInfo<Vip>> list(@PathVariable int page){
    //设置当前页码和每页显示的条数
    PageHelper.startPage(page, Constant.PAGE_SIZE);
    //查询数据
    List<Vip> vips = vipService.findAll();
    //封装数据到PageInfo对象当中
    PageInfo<Vip> vipPageInfo = new PageInfo<>(vips);
    return R.ok(vipPageInfo);
}
}
