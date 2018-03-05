package com.grandland.qdhx.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.domain.BusinessClassify;
import com.grandland.qdhx.treedata.Node;
import com.grandland.qdhx.treedata.TreeBuilder;
import com.grandland.qdhx.web.service.BusinessClassifyService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

@Service
public class BusinessClassifyServiceImpl extends BaseServiceImpl<BusinessClassify>  implements BusinessClassifyService {
	
	public ResponseDate<List<Node>> getBusinessClassify() {
		
	  BusinessClassify businessClassify = new BusinessClassify();
	  List<BusinessClassify> list = selectByExample(businessClassify,null);
	  List<Node> nodes = new ArrayList<>();
	  for(BusinessClassify busi:list) {
		  Node node = new Node();
		  BeanUtils.copyProperties(busi ,node);
		  nodes.add(node);
	  }
	  
	  //拼装树形json字符串
      List<Node> resnodes= new TreeBuilder<Node>().buildTree(nodes);
      ResponseDate<List<Node>> res = new ResponseDate<List<Node>>();
      res.setData(resnodes);
      return res;
	}
}
