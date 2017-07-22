package com.preston.argiope.model.dev;

public class IpBlockingResetResponse {
	
	public enum IpBlockingResetResult {
		INVALID_CREDENTIALS,
		IP_NOT_BLOCKED,
		FAIL,
		SUCCESS
	}

	private IpBlockingResetResult result;
	private String ip;

	public IpBlockingResetResult getResult() {
		return result;
	}
	public void setResult(IpBlockingResetResult result) {
		this.result = result;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
