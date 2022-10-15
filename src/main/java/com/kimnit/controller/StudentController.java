package com.kimnit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimnit.common.R;
import com.kimnit.dto.StudentDto;
import com.kimnit.entity.Admin;
import com.kimnit.entity.Card;
import com.kimnit.entity.Dormm;
import com.kimnit.entity.Student;
import com.kimnit.service.ICardService;
import com.kimnit.service.IDormmService;
import com.kimnit.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-09-08
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IDormmService iDormmService;

    @Autowired
    private ICardService iCardService;

    @GetMapping
    public R<Page> page(int page, int rows, String name){
        log.info ("page = {},rows = {}, name = {}", page, rows, name);

        Page<Student> studentPage = new Page<Student> ( page,rows );
        Page<StudentDto> dtoPage = new Page<StudentDto> ( page,rows );

        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper (  );
        //添加一个过滤条件
        queryWrapper.like (StringUtils.hasText (name), Student::getName,name);
        //添加排序
        queryWrapper.orderByAsc (Student::getId);

        //执行查询
        iStudentService.page (studentPage, queryWrapper);

        List<Student> records = studentPage.getRecords ( );
        List<StudentDto> studentDtos = records.stream ().map ((item) -> {

            StudentDto studentDto = new StudentDto ();

            BeanUtils.copyProperties (item,studentDto);

            LambdaQueryWrapper<Dormm> dormmLambdaQueryWrapper = new LambdaQueryWrapper<> ();
            dormmLambdaQueryWrapper.eq (Dormm::getStuid,item.getUserid ());
            Dormm dormm = iDormmService.getOne (dormmLambdaQueryWrapper);

            studentDto.setFloor (dormm.getFloor ());
            studentDto.setDormmid (dormm.getDormid ());
            return studentDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords (studentDtos);
        dtoPage.setTotal (studentPage.getTotal ());

        return R.success (dtoPage);
    }

    @PutMapping
    public R<String> update(@RequestBody Admin admin){
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<> ();
        queryWrapper.eq (admin.getUserid ()!=null,Student::getUserid,admin.getUserid ());

        Student student = iStudentService.getOne (queryWrapper);
        student.setPhone (admin.getPhone ( ));
        student.setPassword (admin.getPassword ( ));
        iStudentService.updateById (student);

        return R.success ("更新成功");
    }

    @PutMapping("/stu")
    public R<String> updatestu(@RequestBody Map<String,String> dormm){
        String userid = dormm.get ("userid");
        String phone = dormm.get ("phone");
        String password = dormm.get ("password");
        String teacher = dormm.get ("teacher");
        Integer floor = Integer.valueOf (dormm.get ("floor"));
        Integer dormmid = Integer.valueOf (dormm.get ("dormmid"));

        Student student = new Student ();
        student.setUserid (userid);
        student.setPhone (phone);
        student.setPassword (password);
        student.setTeacher (teacher);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<> ();
        queryWrapper.eq (Student::getUserid,userid);
        iStudentService.update ( student, queryWrapper);

        Dormm dormmout = new Dormm ();
        dormmout.setFloor (floor);
        dormmout.setDormid (dormmid);
        LambdaQueryWrapper<Dormm> lambdaQueryWrapper = new LambdaQueryWrapper<> ();
        lambdaQueryWrapper.eq (Dormm::getStuid,userid);
        iDormmService.update(dormmout,lambdaQueryWrapper);

        return R.success ("更新成功");
    }

    @PostMapping
    public R<String> save(@RequestBody Map<String,String> dormm){
        String userid = dormm.get ("userid");
        String name = dormm.get ("name");
        String phone = dormm.get ("phone");
        String password = dormm.get ("password");
        String teacher = dormm.get ("teacher");
        Integer floor = Integer.valueOf (dormm.get ("floor"));
        Integer dormmid = Integer.valueOf (dormm.get ("dormmid"));

        Student student = new Student ();
        student.setUserid (userid);
        student.setName (name);
        student.setPhone (phone);
        student.setPassword (password);
        student.setRole (0);
        student.setTeacher (teacher);
        iStudentService.save (student);

        Dormm dormmout = new Dormm ();
        dormmout.setStuid (userid);
        dormmout.setFloor (floor);
        dormmout.setDormid (dormmid);
        iDormmService.save (dormmout);

        Card card = new Card ();
        card.setStuid (userid);
        card.setSure (0);
        iCardService.save (card);

        return R.success ("新增成功");
    }

    @PostMapping("deleteBatch")
    public R<String> deletelist(@RequestBody List<Integer> list){
        for (int id : list){
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<> ();
            queryWrapper.eq (Student::getId,id);
            Student one = iStudentService.getOne (queryWrapper);
            String userid = one.getUserid ( );

            LambdaQueryWrapper<Dormm> lambdaQueryWrapper = new LambdaQueryWrapper<> ();
            lambdaQueryWrapper.eq (Dormm::getStuid,userid);
            LambdaQueryWrapper<Card> cardLambdaQueryWrapper = new LambdaQueryWrapper<> ();
            cardLambdaQueryWrapper.eq (Card::getStuid,userid);

            iStudentService.removeById (id);
            iCardService.remove (cardLambdaQueryWrapper);
        }

        iDormmService.removeByIds (list);

        return R.success ("批量删除成功");
    }

    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable Integer id){
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<> ();
        queryWrapper.eq (Student::getId,id);
        Student one = iStudentService.getOne (queryWrapper);
        String userid = one.getUserid ( );

        LambdaQueryWrapper<Dormm> lambdaQueryWrapper = new LambdaQueryWrapper<> ();
        lambdaQueryWrapper.eq (Dormm::getStuid,userid);
        LambdaQueryWrapper<Card> cardLambdaQueryWrapper = new LambdaQueryWrapper<> ();
        cardLambdaQueryWrapper.eq (Card::getStuid,userid);

        iStudentService.removeById (id);
        iDormmService.remove (lambdaQueryWrapper);
        iCardService.remove (cardLambdaQueryWrapper);

        return R.success ("删除成功");
    }
}

