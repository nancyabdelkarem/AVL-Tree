package eg.edu.alexu.csd.filestructure.avl;

public class Node<T extends Comparable<T>> implements INode<T> {

	private INode<T> leftChild;
	private INode<T> rightChild;
	private T value;
	private int balancedFactor;
	private int height;



	public int getBalancedFactor() {
		return balancedFactor;
	}

	public void setBalancedFactor(final int balancedFactor) {
		this.balancedFactor = balancedFactor;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	@Override
	public INode<T> getLeftChild() {
		// TODO Auto-generated method stub
		return leftChild;
	}

	@Override
	public INode<T> getRightChild() {
		// TODO Auto-generated method stub
		return rightChild;
	}

	public void setLeftChild(final INode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(final INode<T> rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(final T value) {
		this.value = value;

	}

}
