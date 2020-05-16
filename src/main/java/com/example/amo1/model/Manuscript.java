package com.example.amo1.model;

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
    private Date pulishdate;

    //稿件内容
    private String content;

    //一对一稿件数据
    private ManuscriptData manuscriptData;

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

    public Date getPulishdate() {
        return pulishdate;
    }

    public void setPulishdate(Date pulishdate) {
        this.pulishdate = pulishdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public ManuscriptData getManuscriptData() {
        return manuscriptData;
    }

    public void setManuscriptData(ManuscriptData manuscriptData) {
        this.manuscriptData = manuscriptData;
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
}