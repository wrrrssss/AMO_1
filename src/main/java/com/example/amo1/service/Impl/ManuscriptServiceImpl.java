package com.example.amo1.service.Impl;

import com.example.amo1.mapper.ManuscriptMapper;
import com.example.amo1.model.Manuscript;
import com.example.amo1.service.ManuscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class ManuscriptServiceImpl implements ManuscriptService {

    @Autowired
    private ManuscriptMapper manuscriptMapper;

    @Override
    public void publishManuscript(Manuscript manuscript) {
        String html = manuscript.getContent();
        //处理html特殊标签
        String temp = HtmlUtils.htmlEscape(html);
        manuscript.setContent(temp);
        manuscriptMapper.insert(manuscript);
    }

    @Override
    public Manuscript select(Integer id) {
        Manuscript manuscript = manuscriptMapper.selectByPrimaryKey(id);
        String temp = manuscript.getContent();
        //解析处理了的html语句
        manuscript.setContent(HtmlUtils.htmlUnescape(temp));
        return manuscript;
    }

    @Override
    public void delete(Integer id) {
        manuscriptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Manuscript manuscript) {
        manuscriptMapper.updateByPrimaryKey(manuscript);
    }
}
