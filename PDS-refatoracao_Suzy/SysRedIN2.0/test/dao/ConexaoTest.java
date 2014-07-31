package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConexaoTest {

	@Test
	public void testGetInstance() {
		assertNotNull(Conexao.getInstance());
	}

	@Test
	public void testGetConnection() {
		assertNotNull(Conexao.getConnection());
	}

}
