package com.lh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.api.product.ISearchService;
import com.lh.entity.Product;
import com.lh.entity.ProductExample;
import com.lh.mapper.ProductMapper;
import com.lh.shop.common.pojo.PageResultBean;
import com.lh.shop.common.pojo.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by laiHom on 2020/3/8.
 */
@Component
@Service
public class SearchService implements ISearchService {


    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean initAllData() {
        //1.获取到数据库的数据
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andProductStateEqualTo(1);
        List<Product> list = productMapper.selectByExample(productExample);
        //2.将数据转换成document，再将保存到solr中
        for (Product product : list) {
            //product->document
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",product.getProductId());
            document.setField("product_name",product.getProductName());
            document.setField("product_images",product.getProductOneImage());
            document.setField("product_price",product.getShopPrice().floatValue());
            //
            try {
                solrClient.add(document);
            } catch (SolrServerException | IOException e) {
                //打印到控制台
                e.printStackTrace();
                //记录日志
                //记录到日志表
                //异常信息管理---索引库异常信息
                //发送一封邮件和发送短信给相关负责人
                return ResultBean.error("添加到索引库失败！");
            }
        }
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return ResultBean.error("添加到索引库失败！");
        }
        return ResultBean.success("数据同步成功！");
    }

    @Override
    public List<Product> searchByKeyWord(String keyWord) {
        //1.组装查询条件
        SolrQuery queryCondition = new SolrQuery();
        if(!StringUtils.isAllEmpty(keyWord)){
            queryCondition.setQuery("product_keywords:"+keyWord);
        }else{
            queryCondition.setQuery("product_keywords:华为");
        }
        //2.增加一个高亮的效果
        queryCondition.setHighlight(true);
        queryCondition.addHighlightField("product_name");
        queryCondition.setHighlightSimplePre("<font color='red'>");
        queryCondition.setHighlightSimplePost("</font>");

        List<Product> results = null;
        try {
            //2.执行查询
            QueryResponse response = solrClient.query(queryCondition);
            SolrDocumentList list = response.getResults();

            //获取到高亮的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            //
            results = new ArrayList<>(list.size());
            //3.将查询结果List<Docuemnt>转换为List<Product>
            for (SolrDocument document : list) {
                //document->product
                Product product = new Product();
                product.setProductId((int) Long.parseLong(document.getFieldValue("id").toString()));
                //product.setName(document.getFieldValue("product_name").toString());
                product.setProductOneImage(document.getFieldValue("product_images").toString());
                product.setShopPrice(NumberUtils.createBigDecimal(document.get("product_price").toString()));
                //product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                //获取商品名称的高亮信息
                //1，获取到当前这条记录的高亮信息
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id").toString());
                //2，获取商品名称的字段的高亮信息
                List<String> productNameHighlight = map.get("product_name");
                //3.单独处理高亮的设置
                if(productNameHighlight!= null && productNameHighlight.size() > 0){
                    //如果本次是按照商品名称搜索到的记录
                    product.setProductName(productNameHighlight.get(0));
                }else{
                    product.setProductName(document.getFieldValue("product_name").toString());
                }

                results.add(product);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public PageResultBean<Product> searchByKeyWord(String keyWord, Integer pageIndex, Integer rows) {
        PageResultBean<Product> pageResultBean = new PageResultBean<>();
        //1.组装查询条件
        SolrQuery queryCondition = new SolrQuery();
        if(!StringUtils.isAllEmpty(keyWord)){
            queryCondition.setQuery("product_keywords:"+keyWord);
        }else{
            queryCondition.setQuery("product_keywords:华为");
        }
        //2.增加一个高亮的效果
        queryCondition.setHighlight(true);
        queryCondition.addHighlightField("product_name");
        queryCondition.addHighlightField("product_sale_point");
        queryCondition.setHighlightSimplePre("<font color='red'>");
        queryCondition.setHighlightSimplePost("</font>");

        //3.增加分页
        queryCondition.setStart((pageIndex-1)*rows);
        queryCondition.setRows(rows);

        List<Product> results = null;

        long totalCount = 0L;
        try {
            //2.执行查询
            QueryResponse response = solrClient.query(queryCondition);
            SolrDocumentList list = response.getResults();
            totalCount = list.getNumFound();

            //获取到高亮的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            //
            results = new ArrayList<>(list.size());
            //3.将查询结果List<Docuemnt>转换为List<Product>
            for (SolrDocument document : list) {
                //document->product
                Product product = new Product();
                product.setProductId((int) Long.parseLong(document.getFieldValue("id").toString()));
                //product.setName(document.getFieldValue("product_name").toString());
                product.setProductOneImage(document.getFieldValue("product_images").toString());
                product.setShopPrice(NumberUtils.createBigDecimal(document.get("product_price").toString()));
                //product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                //获取商品名称的高亮信息
                //1，获取到当前这条记录的高亮信息
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id").toString());
                //2，获取商品名称的字段的高亮信息
                List<String> productNameHighlight = map.get("product_name");
                //3.单独处理高亮的设置
                if(productNameHighlight!= null && productNameHighlight.size() > 0){
                    //如果本次是按照商品名称搜索到的记录
                    product.setProductName(productNameHighlight.get(0));
                }else{
                    product.setProductName(document.getFieldValue("product_name").toString());
                }

                results.add(product);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pageResultBean.setPageNum(pageIndex);
        pageResultBean.setPageSize(rows);
        pageResultBean.setList(results);
        pageResultBean.setTotal(totalCount);
        pageResultBean.setPages((int) (totalCount%rows==0?(totalCount/rows):(totalCount/rows)+1));
        return pageResultBean;
    }
}
