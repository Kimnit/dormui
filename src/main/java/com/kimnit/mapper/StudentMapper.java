package com.kimnit.mapper;

import com.kimnit.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-09-08
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
