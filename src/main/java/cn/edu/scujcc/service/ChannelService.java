package cn.edu.scujcc.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;
/**
 * �ṩƵ����ص�ҵ���߼�
 * @author DELL
 *
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	
	/**
	 * ��ȡ����Ƶ��������
	 * @return	Ƶ��List
	 */
	public List<Channel> getAllChannels(){
		repo.findByTitleContaining("����"); 			//����ֵΪ�������������ֵ�ֵ
		return repo.findAll();
	}
	/**
	 * ��ȡһ��Ƶ��
	 * @param id
	 * @return
	 */
	public Channel getChannel(String channelId) {
		
		 Optional<Channel> result=repo.findById(channelId);
		 if(result.isPresent()) {
			 return result.get();
		 }else {
			 return null;
		 }
		 
	}
	
	/**
	 * ɾ��ָ��Ƶ��
	 * @param id
	 * @return
	 */
	public boolean deleteChannel(String channelId) { 
		boolean result = true ;
		repo.deleteById(channelId);
		
		return result;
	}
	/**
	 * ����һ��Ƶ��
	 * @param c�����µ�Ƶ��
	 * @return���º��Ƶ��
	 */
	public Channel updateChannel(Channel c) {
		 Channel saved =getChannel(c.getId());
		 if(saved !=null) {
			 if(c.getTitle()!=null) {
				 saved.setTitle(c.getTitle());
			 }
			 if(c.getQuality()!=null) {
				 saved.setQuality(c.getQuality());
			 }
			 if(c.getUrl()!=null) {
				 saved.setUrl(c.getUrl());
			 }
			 if(c.getComments()!=null) {
				 saved.getComments().addAll(c.getComments());
			 }else {
				 saved.setComments(c.getComments());
			 }
		 }
		return repo.save(saved);
	}
	/**
	 * �½�Ƶ��
	 * @param c
	 * @return
	 */
	public Channel createChannel(Channel c) {
//		this.channels.add(c);
//		c.setId(this.channels.get(this.channels.size()-1).getId()+1);
//		this.channels.add(c);
//		return c;
		return repo.save(c);
	}
	/**
	 * ��������
	 * @param title
	 * @param quality
	 * @return
	 */
	public List<Channel> searchByTitle(String title){
		return repo.findByTitle(title);
	}
	public List<Channel> searchByQuality(String quality){
		return repo.findByQuality(quality);
}
	public List<Channel> getLatestCommentsChannel(){
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
		return repo.findByCommentsDtAfter(today);
	}
}
