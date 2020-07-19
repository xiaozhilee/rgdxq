package com.example.demo.dao;

import com.example.demo.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface ExpenseDao extends JpaRepository<Expense,Long> {


    @Query(value = "select  * from expense where url = ?1 and ttype = 0 order by ddate  desc limit 0,3",nativeQuery = true)
    List<Expense> getErrorMessageByUrl(String url);

    @Query(value = "select  * from expense where url like ?1 order by ddate  desc limit 0,100",nativeQuery = true)
    List<Expense> getMessageByUrl(String url);

    @Query("select count(expense) from Expense where url = ?1 and ttype = 0 and ddate between ?2 and ?3")
    int type0num(String url,Date date1,Date date2);

    @Query("select count(expense) from Expense where url = ?1 and ttype = 1 and ddate between ?2 and ?3")
    int type1num(String url, Date date1,Date date2);

    @Query("select avg(expense) from Expense where url = ?1 and ddate between  ?2 and ?3")
    Double AVGdevice( String url, Date date1, Date date2);
}
