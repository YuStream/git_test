package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ArticleDao articleDao;

    public Article findById(String id){
        //先去redis中去查找
        Article article = (Article) redisTemplate.opsForValue().get("article_"+id);
        if (article == null){
            //如果没有数据再去数据库中查找
            Optional<Article> optional = articleDao.findById(id);
            Article article1 = optional.get();
            //然后放到redis中
            redisTemplate.opsForValue().set("article_"+id, article1);
        }
        return article;
    }

    //删除或修改清除缓存
    public void update(Article article){
        redisTemplate.delete("article_"+article.getId());
        articleDao.save(article);
    }

    public void delete(String id){
        redisTemplate.delete("article_"+id);
        articleDao.deleteById(id);
    }
}
