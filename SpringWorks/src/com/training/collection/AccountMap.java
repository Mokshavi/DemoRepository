package com.training.collection;

import java.util.Map;

public class AccountMap {

	private Map<String,Long> accountMap;

	@Override
	public String toString() {
		return "AccountMap [accountMap=" + accountMap + "]";
	}

	public Map<String, Long> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<String, Long> accountMap) {
		this.accountMap = accountMap;
	}
	
}
