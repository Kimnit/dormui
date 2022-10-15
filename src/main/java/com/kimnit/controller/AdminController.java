package com.kimnit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimnit.common.R;
import com.kimnit.entity.Admin;
import com.kimnit.entity.Dormm;
import com.kimnit.entity.Student;
import com.kimnit.service.IAdminService;
import com.kimnit.service.IDormmService;
import com.kimnit.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-09-08
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IDormmService iDormmService;

    @PostMapping("/login")
    public R<Admin> login(@RequestBody Admin admin) {

        log.info ("admin={}", admin);

        if (admin.getRole ( ) == 1) {
            LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<> ( );
            queryWrapper.eq (null != admin.getUserid ( ), Admin::getUserid, admin.getUserid ( ));
            queryWrapper.eq (Admin::getPassword, admin.getPassword ( ));

            Admin admin1 = iAdminService.getOne (queryWrapper);

            if (admin1 != null) {
                return R.success (admin1);
            } else {
                return R.error ("密码错误");
            }
        } else {
            LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<> ( );
            lambdaQueryWrapper.eq (null != admin.getUserid ( ), Student::getUserid, admin.getUserid ( ));
            lambdaQueryWrapper.eq (Student::getPassword, admin.getPassword ( ));
            Student student = iStudentService.getOne (lambdaQueryWrapper);
            if (student == null){
                return R.error ("密码错误");
            }else {
                int userid = Integer.parseInt (student.getUserid ( ));
                LambdaQueryWrapper<Dormm> queryWrapper = new LambdaQueryWrapper<> ();
                queryWrapper.eq (Dormm::getStuid,userid);
                Dormm dormm = iDormmService.getOne (queryWrapper);

                Admin adminout = new Admin ();
                adminout.setRole (0);
                adminout.setName (student.getName ());
                adminout.setUserid (student.getUserid ());
                adminout.setPhone (student.getPhone ());
                adminout.setPassword (student.getPassword ());
                adminout.setId (dormm.getFloor ());
                adminout.setManage (dormm.getDormid ());

                return R.success (adminout);
            }
        }
    }


    @GetMapping
    public R<Page<Admin>> page(int page, int rows, String name){
        log.info ("page = {},rows = {}, name = {}", page, rows, name);

        Page adminPage = new Page( page,rows );

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper (  );
        //添加一个过滤条件
        queryWrapper.like (StringUtils.hasText (name), Admin::getName,name);
        //添加排序
        queryWrapper.orderByAsc (Admin::getId);

        //执行查询
        iAdminService.page (adminPage, queryWrapper);

        return R.success (adminPage);
    }

    @PutMapping
    public R<String> update(@RequestBody Admin admin){

        iAdminService.updateById (admin);

        return R.success ("更新成功");
    }
}

