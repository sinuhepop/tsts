package tk.spop.tml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lombok.Getter;
import lombok.NonNull;

public class XmlTag implements Tag<XmlTag>{

	@NonNull
	private String name;
	private List<XmlTag> children = new ArrayList<>();
	private Map<String, String> attributes = new TreeMap<>();

	@Getter
	private boolean frozen = false;

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> attributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XmlTag> children() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
