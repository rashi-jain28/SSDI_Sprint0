package com.sample.DAOImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.sample.DAO.IStudentDAO;
import com.sample.model.Student;
import com.sample.service.IStudentService;

@Repository
public class StudentDAOImpl extends JdbcDaoSupport implements IStudentDAO{

        @Autowired 
        DataSource dataSource;
        
        @PostConstruct
        private void initialize(){
            setDataSource(dataSource);
        }
        
        public List<Student> fetchStudents() {
            // TODO Auto-generated method stub
            String sql = "SELECT * FROM studentInfo";
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);        
            List<Student> result = new ArrayList<Student>();
            for(Map<String, Object> row:rows){
                Student s = new Student();            
                s.setSid(((String)row.get("sid")));
                s.setName(((String)row.get("name")));
                s.setAge((Integer)row.get("age"));            
                result.add(s);
            }
            
            return result;
        }
}