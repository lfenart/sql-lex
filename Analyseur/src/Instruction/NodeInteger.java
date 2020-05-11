package Instruction;

public class NodeInteger extends NodeNumeric {

	private Long value;

	public NodeInteger(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return this.value;
	}

}
