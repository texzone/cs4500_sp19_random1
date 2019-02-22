package com.example.cs4500_sp19_random1.models;

import java.util.List;

public class SearchCriteria {
    private List<SearchPredicate> predicates;

    public SearchCriteria(List<SearchPredicate> predicates){
        this.predicates = predicates;
    }

    public List<SearchPredicate> getPredicates() {
        return this.predicates;
    }
    public void setPredicates(List<SearchPredicate> predicates){
        this.predicates = predicates;
    }
}
