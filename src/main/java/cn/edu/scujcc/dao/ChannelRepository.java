package cn.edu.scujcc.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import cn.edu.scujcc.model.Channel;


public interface ChannelRepository extends MongoRepository<Channel,String>{
	public List<Channel> findByTitle(String t);
	public List<Channel> findByQuality(String q);
	public List<Channel> findByTitleContaining(String t);
	
	//�ҳ�����ʱ����ָ������֮�������Ƶ��
	public List<Channel> findByCommentsDtAfter(LocalDateTime theDt);
}

