package cn.edu.scujcc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.model.Channel;
import cn.edu.scujcc.service.ChannelService;

/**
 * 频道接口，提供客户端访问的入口
 * @author DELL
 *
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {
	public static final Logger Logger = LoggerFactory.getLogger(ChannelController.class);
	@Autowired
	private ChannelService service;
	/**
	 * 获取所有频道
	 * @return 所有频道的JSON数组
	 */
	@GetMapping
	public List<Channel> getAllChannels() {
		Logger.info("正在查找所有频道信息...");
		List<Channel>results = service.getAllChannels();
			Logger.debug("所有频道的数量是："+results.size());
			return results;
	}
	
	/**
	 * 获取一个指定频道的JSON数据
	 * @param id 指定频道的编号
	 * @return id 对应频道的JSON数据
	 */
	@GetMapping("/{id}")
	public Channel getChannel(@PathVariable String id) {
		Channel c =service.getChannel(id);
		if(c!=null) {
			return c;
		}else {
			Logger.error("找不到指定的频道");
			return null;
		}
	}
	/**
	 * 删除一个指定的频道
	 * @param id 带删除频道的编号
	 * 
	 * @return 成功或失败的信息
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteChannel(@PathVariable String id) {
		System.out.println("即将删除频道"+ id);
		boolean result = service.deleteChannel(id);
		if(result) {
			return ResponseEntity.ok().body("删除成功");
		}
		else {
			return ResponseEntity.ok().body("删除失败");
		}
	}
	/**
	 * 新建一个频道
	 * @param c 待新建频道的数据
	 * @return 保存后的频道数据
	 */
	@PostMapping //不写，则默认网址
	public Channel createChannel(@RequestBody Channel c) {
		System.out.println("即将创建频道"+c);
		Channel saved=service.createChannel(c);
		return saved;
	}
	/**
	 * 
	 * @param c
	 * @return
	 */
	@PutMapping
	public Channel updateChannel(@RequestBody Channel c) {
		System.out.println("即将更新频道"+c);
		Channel updated=service.updateChannel(c);
		return updated;
	}
	
	@GetMapping("/t/{title}")
	public List<Channel> searchByTitle(@PathVariable String title ){
		return service.searchByTitle(title);
	}
	@GetMapping("/q/{quality}")
	public List<Channel> searchByQuality(@PathVariable String quality){
		return service.searchByQuality(quality);
	}
	@GetMapping("/hot")
	public List<Channel> getHotChannels(){
		return service.getLatestCommentsChannel();
	}
}
