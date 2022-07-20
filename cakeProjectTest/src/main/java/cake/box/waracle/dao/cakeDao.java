package cake.box.waracle.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cake.box.waracle.dto.cakeEntity;
@Repository
public class cakeDao {

	@Autowired
	NamedParameterJdbcTemplate temp;
	
	
	
	public List<cakeEntity> getCakes(){
		
		String query="select Id,title,status,desc,image from cakes where status=:status";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("status", "Active");
		List<Map<String,Object>> lst =temp.queryForList(query, params);
		List<cakeEntity> lst1=new ArrayList<cakeEntity>();

		for(Map<String,Object> map : lst) {
			cakeEntity ce = new cakeEntity();

			ce.setTitle( (String) map.get("title"));
			ce.setImage((String) map.get("image"));
			ce.setStatus((String) map.get("status"));
			ce.setDesc((String) map.get("desc"));
			ce.setId((Integer) map.get("Id"));
			lst1.add(ce);



		}
		return lst1;
	
		
		
	}
	
}
