package com.allqj.virtual_number_administrate.business.repository.elasticsearch;


import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.DeptInfoEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface IDeptInfoEsRepository extends ElasticsearchRepository<DeptInfoEsEntity, Integer> {

}
