package dao;

import org.springframework.core.GenericTypeResolver;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Resource;
import javax.sql.DataSource;

// Adaptador

public class Dao<T> {
	
protected JdbcTemplate jdbcTemplate;

	protected String tableName = "";
	private SqlGenerator sqlUtil; // Helper para construção das queries
	private final Class<T> genericType; // registra o tipo da classe que está executando o método
	
	@SuppressWarnings("unchecked")
	public Dao(){
		this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), Dao.class);
		this.sqlUtil = new SqlGenerator();
	}
	
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public SqlGenerator getSqlUtil(){
		return this.sqlUtil;
	}
	
	// TODO: Utilizar JdbcDaoSupport
	// http://www.mkyong.com/spring/spring-jdbctemplate-jdbcdaosupport-examples/
	protected JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
	
	/*
	 * Find Methods
	 *
	 * Efetua uma busca personalizada
	 * A query é montada dinamicamente de acordo com o tipo de busca desejada (all, first, list)
	 *
	 */
	public List<T> find(String method) throws JSONException {
		return this.find(method, new JSONObject());
	}
	public List<T> find(String method, JSONObject parameters) throws JSONException {
        try{
            if(parameters == null) parameters = new JSONObject();
            
            if( !parameters.has("limit") || parameters.getInt("limit") <= 0 ){
                parameters.put("limit", 10);
            }

            String sql = this.getSqlUtil().buildFind(method, parameters, this.tableName);

            return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<T>( this.genericType ));
        }catch (EmptyResultDataAccessException e){
            System.out.println("Nenhum registro encontrado para " + this.tableName);
            e.printStackTrace();
        }
        return null;
	}
}
