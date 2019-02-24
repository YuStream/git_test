package com.tensquare.base.service;

import com.sun.org.apache.regexp.internal.RE;
import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     * @return
     */
    public List<Label> findAll(){
        List<Label> labelList = labelDao.findAll();
        return labelList;
    }

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 根据条件查询
     * @param map
     * @return
     */
    public List<Label> findSearch(Map map){
       Specification<Label> spec = createSpecification(map);
       return labelDao.findAll(spec);
    }

    /**
     * 有分页的条件查询
     * @param map
     * @param page
     * @param size
     * @return
     */
    public Page<Label>findSearch(Map map,int page,int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        Specification<Label> specification = createSpecification(map);
        Page<Label> all = labelDao.findAll(specification, pageable);
        return all;
    }

    private Specification<Label> createSpecification(Map map) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                ArrayList<Object> list = new ArrayList<>();
                if (map.get("labelname")!=null && !"".equals(map.get("labelname"))){
                    Predicate p1 = cb.like(root.get("labelname").as(String.class), "%" + map.get("labelname") + "%");
                    list.add(p1);
                }
                if (map.get("state")!=null && !"".equals(map.get("state"))){
                    Predicate p2 = cb.equal(root.get("state").as(String.class), map.get("state"));
                    list.add(p2);
                }
                if (map.get("recommend")!=null && !"".equals(map.get("recommend"))){
                    Predicate p3 = cb.equal(root.get("recommend").as(String.class), map.get("recommend"));
                    list.add(p3);
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return spec;
    }

    /**
     * 增加标签
     * @param label
     */
    public void save(Label label){
        label.setId(idWorker.nextId()+"");
         labelDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void delete(String id){
        labelDao.deleteById(id);
    }
}
