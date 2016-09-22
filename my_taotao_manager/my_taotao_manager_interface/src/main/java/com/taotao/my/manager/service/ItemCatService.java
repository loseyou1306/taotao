package com.taotao.my.manager.service;

import java.util.List;

import com.taotao.my.manager.utils.EasyUiTreeNode;

public interface ItemCatService {
	/**
	 * 传递参数:父节点的id;
	 * 返回值:treeNode ison格式的数据List<EasyUiTreeNode>
	 * @param parent_id
	 * @return
	 */
	public List<EasyUiTreeNode> findItemCatList(Long parent_id);
}
