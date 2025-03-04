package com.huling.infrastructure.dao;

import com.huling.infrastructure.dao.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface IRuleTreeNodeDao {

    List<RuleTreeNode> queryRuleTreeNodeListByTreeId(String treeId);

    List<RuleTreeNode> queryRuleLocks(String[] treeIds);

}
