package com.example.amo1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.util.Date;

/**
 * @Mapping注解来自定义生成Mapping
 * @Document 作用在类，标记实体类为文档对象，一般有两个属性
 *           indexName：对应索引库名称
 *           type：对应在索引库中的类型
 *           shards：分片数量，默认5
 *           replicas：副本数量，默认1
 *
 * @Id 作用在成员变量，标记一个字段作为id主键
 *
 * @Field 作用在成员变量，标记为文档的字段，并指定字段映射属性：
 *         type：字段类型，是是枚举：FieldType
 *         index：是否索引，布尔类型，默认是true
 *         store：是否存储，布尔类型，默认是false
 *           analyzer：分词器名称
 *
 */
@Mapping(mappingPath = "/json/manuscript_mapping.json")
@Document(indexName = "manuscript",type="doc",shards = 1,replicas = 0)
public class Manuscript {
    //稿件id
    @Id
    private Integer id;

    //用户id
    private Integer userId;

    //用户名
    private String username;

    //标题
    private String title;

    //封面
    private String cover;

    //发布稿件日期
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishdate;

    //稿件内容
    private String content;

    //点赞数
    private Integer love;

    //转发数
    private Integer forward;

    //评论数
    private Integer comment;

    //收藏数
    private Integer collection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date pulishdate) {
        this.publishdate = publishdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Integer getForward() {
        return forward;
    }

    public void setForward(Integer forward) {
        this.forward = forward;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }
}