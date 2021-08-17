package com.yule.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class EchartsOptionsVo implements Serializable {

	/**
	 * echarts 设置参数
	 */
	private static final long serialVersionUID = 7894778787753733238L;

	private Map<String, Object> tooltip;
	private Map<String, Object> legend;
	private Map<String, Object> toolbox;
	private boolean calculable;
	private Map<String, Object> xAxis;
	private Map<String, Object> yAxis;
	private List<Map<String, Object>> series;
	
	
	public Map<String, Object> getTooltip() {
		return tooltip;
	}

	public void setTooltip(Map<String, Object> tooltip) {
		this.tooltip = tooltip;
	}

	public Map<String, Object> getLegend() {
		return legend;
	}

	public void setLegend(Map<String, Object> legend) {
		this.legend = legend;
	}

	public Map<String, Object> getToolbox() {
		return toolbox;
	}

	public void setToolbox(Map<String, Object> toolbox) {
		this.toolbox = toolbox;
	}

	public boolean isCalculable() {
		return calculable;
	}

	public void setCalculable(boolean calculable) {
		this.calculable = calculable;
	}

	public Map<String, Object> getxAxis() {
		return xAxis;
	}

	public void setxAxis(Map<String, Object> xAxis) {
		this.xAxis = xAxis;
	}

	public Map<String, Object> getyAxis() {
		return yAxis;
	}

	public void setyAxis(Map<String, Object> yAxis) {
		this.yAxis = yAxis;
	}

	public List<Map<String, Object>> getSeries() {
		return series;
	}

	public void setSeries(List<Map<String, Object>> series) {
		this.series = series;
	}
	
	public EchartsOptionsVo() {
		super();
	}

}
