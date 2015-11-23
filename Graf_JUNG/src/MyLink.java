class MyLink {
	double capacity; // should be private
	double weight; // should be private for good practice
	int id;
	String name;
	double weightForCritical;
	double offset = 0;

	public MyLink(double weight, double capacity, String name) {
		//this.id = edgeCount++; // This is defined in the outer class.
		this.weight = weight;
		this.weightForCritical = weight;
		this.capacity = capacity;
		this.name = name;
	}

	public String toString() { // Always good for debugging
		return " " + name + " [" + capacity + "]";
	}
	
	public String toStringWithCapacity() {
		return " " + name +  "[" + capacity + "]";
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeightForCritical() {
		return weightForCritical;
	}

	public void setWeightForCritical(double weightForCritical) {
		this.weightForCritical = weightForCritical;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

}
