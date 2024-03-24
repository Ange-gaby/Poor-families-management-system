/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Province;
import java.util.List;

/**
 *
 * @author Gasana
 */
public class ProvinceDao extends GenericDao<Province>{
    public List<Province> allProvinces() {
        return this.findAll(Province.class);
    }
    public Province findProvince(Integer id) {
        return this.findOne(Province.class, id);
    }
}
