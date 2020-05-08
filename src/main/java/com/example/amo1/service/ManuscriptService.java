package com.example.amo1.service;

import com.example.amo1.model.Manuscript;

public interface ManuscriptService {
    void publishManuscript(Manuscript manuscript);

    Manuscript select(Integer id);

    void delete(Integer id);

    void update(Manuscript manuscript);

}
