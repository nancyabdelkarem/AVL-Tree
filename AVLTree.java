package eg.edu.alexu.csd.filestructure.avl;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {
	INode<T> root;
	private int nodeCount = 0;
	private static final int zero = 0;

	@Override
	public void insert(final T key) {
		if (key == null) {
			return;
		}

		root = insert(root, key);
		nodeCount++;

	}

	private INode<T> insert(final INode<T> node, final T key) {
		INode<T> pivot = new Node<T>();
		if (node == null) {
			final INode<T> node1 = new Node<T>();
			node1.setValue(key);
			return node1;
		}
		final int cmp = key.compareTo(node.getValue());
		if (cmp > 0) {
			pivot = insert(node.getRightChild(), key);
			((Node<T>) node).setRightChild(pivot);
		} else if (cmp < 0 || cmp == 0) {
			pivot = insert(node.getLeftChild(), key);
			((Node<T>) node).setLeftChild(pivot);
		} else {
			;
		}
		// Update balance factor and height values.
		update(node);

		// Re-balance tree.
		return balance(node);
	}

	// Re-balance a node if its balance factor is +2 or -2
	private INode<T> balance(final INode<T> node) {
		// Left heavy subtree.
		if (((Node<T>) node).getBalancedFactor() == -2) {
			// left-left case
			if (((Node<T>) node.getLeftChild()).getBalancedFactor() <= 0) {
				return leftLeftCase(node);
			} else {
				return leftRightCase(node);
			}

		} else if (((Node<T>) node).getBalancedFactor() == +2) {
			// right-right case
			if (((Node<T>) node.getRightChild()).getBalancedFactor() >= 0) {
				return rightRightCase(node);
			} else {
				return rightLeftCase(node);

			}
		}
		return node;

	}

	private INode<T> rightLeftCase(final INode<T> node) {
		((Node<T>) node).setRightChild(rightRotation(node.getRightChild()));
		return rightRightCase(node);
	}

	private INode<T> leftRightCase(final INode<T> node) {
		((Node<T>) node).setLeftChild(leftRotation(node.getLeftChild()));
		return leftLeftCase(node);
	}

	private INode<T> rightRightCase(final INode<T> node) {

		return leftRotation(node);
	}

	private INode<T> leftLeftCase(final INode<T> node) {

		return rightRotation(node);
	}

	private INode<T> leftRotation(final INode<T> node) {
		final INode<T> parent = node.getRightChild();
		((Node<T>) node).setRightChild(parent.getLeftChild());
		((Node<T>) parent).setLeftChild(node);
		update(node);
		update(parent);

		return parent;
	}

	private INode<T> rightRotation(final INode<T> node) {
		final INode<T> parent = node.getLeftChild();
		((Node<T>) node).setLeftChild(parent.getRightChild());
		((Node<T>) parent).setRightChild(node);
		update(node);
		update(parent);

		return parent;
	}

	private void update(final INode<T> node) {

		final int leftNodeHeight = (node.getLeftChild() == null) ? -1 : ((Node<T>) node.getLeftChild()).getHeight();
		final int rightNodeHeight = (node.getRightChild() == null) ? -1 : ((Node<T>) node.getRightChild()).getHeight();
		((Node<T>) node).setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));
		((Node<T>) node).setBalancedFactor(rightNodeHeight - leftNodeHeight);

	}

	@Override
	public boolean delete(final T key) {
		if (key == null) {
			return false;
		}

		if (!search(root, key)) {
			return false;
		}
		root = delete(root, key);
		nodeCount--;
		return true;
	}

	private INode<T> delete(final INode<T> node, final T key) {
		if (node == null) {
			return null;
		}
		final int cmp = key.compareTo(node.getValue());
		if (cmp < 0) {
			((Node<T>) node).setLeftChild(delete(node.getLeftChild(), key));
		} else if (cmp > 0) {
			((Node<T>) node).setRightChild(delete(node.getRightChild(), key));
		}
		// Found the node we wish to remove.
		else {

			if (node.getLeftChild() == null) {
				return node.getRightChild();
			} else if (node.getRightChild() == null) {
				return node.getLeftChild();
			} else {
				if (((Node<T>) node.getLeftChild()).getHeight() > ((Node<T>) node.getRightChild()).getHeight()) {

					final T successorValue = findMax(node.getLeftChild());
					node.setValue(successorValue);
					((Node<T>) node).setLeftChild(delete(node.getLeftChild(), successorValue));

				} else {
					final T successorValue = findMin(node.getRightChild());
					node.setValue(successorValue);
					((Node<T>) node).setRightChild(delete(node.getRightChild(), successorValue));

				}

			}
		}
		update(node);
		return balance(node);
	}

	private T findMin(INode<T> node) {
		while (node.getLeftChild() != null) {
			node = node.getLeftChild();
		}
		return node.getValue();
	}

	private T findMax(INode<T> node) {
		while (node.getRightChild() != null) {
			node = node.getRightChild();
		}
		return node.getValue();
	}

	@Override
	public boolean search(final T key) {

		return search(this.root, key);
	}

	private boolean search(final INode<T> node, final T key) {
		if (node == null) {
			return false;
		}

		final int cmp = key.compareTo(node.getValue());

		if (cmp > 0) {
			return search(node.getRightChild(), key);
		}
		if (cmp < 0) {
			return search(node.getLeftChild(), key);
		}

		return true;
	}

	@Override
	public int height() {

		if (this.root == null) {
			return zero;
		}

		return ((Node<T>) root).getHeight() + 1;
	}

	@Override
	public INode<T> getTree() {
		if (size() == zero) {
			return null;
		}
		return this.root;
	}

	public int size() {
		return nodeCount;
	}

}
