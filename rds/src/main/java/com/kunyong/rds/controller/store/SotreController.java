package com.kunyong.rds.controller.store;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kunyong.rds.entity.org.Org;
import com.kunyong.rds.service.StoreService;
import com.kunyong.rds.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/5
 */
@Controller
@RequestMapping("/store")
public class SotreController {

    @Autowired
    private StoreService storeService;


    @RequestMapping
    public String initPage() {
        return "store/storeList";
    }


    @RequestMapping("/storeList")
    @ResponseBody
    public Page storeList(Pageable pageable) {
        return storeService.storeList (pageable);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseUtil all(){
        return new ResponseUtil(storeService.all());
    }

    @RequestMapping("/storeConfig")
    public String storeConfig(long id, Model model) {
        model.addAttribute ("id", id);
        return "store/storeConfig";
    }

    @PostMapping(value = "/all")
    @ResponseBody
    public List<Org> findAll() {
        List<Org> list = storeService.findAllList ();
        Map<Integer, Org> map = new HashMap<> ();
        for (Org org : list) {
            map.put (org.getId (), org);
        }
        Map<Integer, List<Org>> pIdMap = new HashMap<> ();
        for (Map.Entry<Integer, Org> en : map.entrySet ()) {
            int id = en.getKey ();
            Org node = en.getValue ();
            List<Org> nodes = pIdMap.get (node.getpId ());
            if ( Objects.isNull (nodes) ) {
                nodes = new ArrayList<> ();
                pIdMap.putIfAbsent (node.getpId (), nodes);
            }
            nodes.add (node);
        }
//转换算法核心:由叶子节到根节点进行处理，也即由下到上处理
        List<Org> nodes = new ArrayList<> ();
        for (Map.Entry<Integer, Org> en : map.entrySet ()) {
            int id = en.getKey ();
            Org node = en.getValue ();
            Org pNode = map.get (node.getpId ());
            //含有父节点就是子节点
            if ( Objects.nonNull (pNode) ) {
                List<Org> sons = pNode.getChild ();
                if ( Objects.isNull (sons) ) {
                    sons = new ArrayList<> ();
                    pNode.setChild (sons);
                }
                sons.add (node);
            } else {//不是子节点就是父节点
                nodes.add (node);
            }
        }

//        JSONArray array = new JSONArray();
//        setResourceTree (-1, list, array);

        return nodes;
    }

    /**
     * 形成树
     *
     * @param pId
     * @param resourceList
     * @param array
     */
    private void setResourceTree(Integer pId, List<Org> resourceList, JSONArray array) {
        for (Org per : resourceList) {
            if ( per.getpId () == pId ) {
                String string = JSONObject.toJSONString (per);
                JSONObject parent = (JSONObject) JSONObject.parse (string);
                array.add (parent);

                if ( resourceList.stream ().filter (p -> p.getpId () == per.getId ()).findAny () != null ) {
                    JSONArray child = new JSONArray ();
                    parent.put ("child", child);
                    setResourceTree (per.getId (), resourceList, child);
                }
            }
        }
    }
}
