package cn.edu.scujcc.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;
/**
 * 提供频道相关的业务逻辑
 * @author DELL
 *
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	
	/**
	 * 获取所有频道的数据
	 * @return	频道List
	 */
	public List<Channel> getAllChannels(){
		repo.findByTitleContaining("中央"); 			//返回值为包含中央两个字的值
		return repo.findAll();
	}
	/**
	 * 获取一个频道
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
	 * 删除指定频道
	 * @param id
	 * @return
	 */
	public boolean deleteChannel(String channelId) { 
		boolean result = true ;
		repo.deleteById(channelId);
		
		return result;
	}
	/**
	 * 更新一个频道
	 * @param c待更新的频道
	 * @return更新后的频道
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
	 * 新建频道
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
	 * 搜索方法
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
