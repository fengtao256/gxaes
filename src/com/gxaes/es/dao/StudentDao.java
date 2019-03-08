package com.gxaes.es.dao;

import com.gxaes.es.entity.Student;

public interface StudentDao {

	Student findByLoginName(String loginName);

	void save(Student stu);

}
