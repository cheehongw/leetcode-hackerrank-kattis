/**
 * @author Wong Chee Hong (A0217558W)
 */
public class GCPC {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        StringBuilder sb = new StringBuilder();
        int n = fio.nextInt();
        int events = fio.nextInt();

        AVLTree tree = new AVLTree();

        TeamScore[] teamList = new TeamScore[n + 1];

        for (int i = 1; i < n + 1; i++) {
            teamList[i] = new TeamScore(i);
            tree.insert(teamList[i]);
        }

        for (int i = 0; i < events; i++) {
            int team = fio.nextInt();
            int newPenalty = fio.nextInt();

            tree.inOrder();
            System.out.print("      ");
            tree.preOrder();

            TeamScore currScore = teamList[team];
            tree.delete(currScore);
            currScore.updatePenalty(newPenalty);
            currScore.incrementSolved();
            tree.insert(currScore);


            System.out.println("");
            sb.append(tree.rank(teamList[1])).append("\n");
        }

        System.out.print(sb);
        fio.close();
    }
}

class TeamScore implements Comparable<TeamScore> {

    int solvedProblems;
    int penalty;
    int team;

    TeamScore(int teamID) {
        this.team = teamID;
        this.penalty = 0;
        this.solvedProblems = 0;
    }

    public void incrementSolved() {
        this.solvedProblems++;
    }

    public void updatePenalty(int penaltyIncrease) {
		penalty += penaltyIncrease;
	}

    @Override
    public int compareTo(TeamScore other) {
        int comp1 = other.solvedProblems - this.solvedProblems;
        int comp2 = this.penalty - other.penalty;
        return comp1 == 0 
            ? comp2 == 0 ? this.team - other.team : comp2 
            : comp1;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", solvedProblems, penalty, team);
    }
}

class AVLVertex implements Comparable<AVLVertex> {

    public AVLVertex left, right;
    public TeamScore value;
    public int height;
    public int size;

    AVLVertex(TeamScore value) {
        this.value = value;
        left = right = null;
        height = 0;
        size = 0;
    }

    public int bf() {
        int leftHeight = left == null ? -1 : left.height;
        int rightHeight = right == null ? -1 : right.height;

        return leftHeight - rightHeight;
    }

    public void updateHeight() {
        int leftHeight = left == null ? -1 : left.height;
        int rightHeight = right == null ? -1 : right.height;

        height = Math.max(leftHeight, rightHeight) + 1;
    }

    public void updateSize() {
        int leftSize = this.left == null ? 0 : this.left.size;
        int rightSize = this.right == null ? 0 : this.right.size;

        size = leftSize + rightSize + 1;
    }

    public AVLVertex findMin() {
        if (this.left == null) {
            return this;
        } else {
            return this.left.findMin();
        }
    }

    @Override
    public int compareTo(AVLVertex other) {
        return this.value.compareTo(other.value);
    }
}

class AVLTree {
    AVLVertex root;

    public AVLTree() {
        root = null;
    }

    public AVLTree(AVLVertex v) {
        root = v;
    }

    public void insert(TeamScore v) {
        root = insert(root, v);
    }

    public AVLVertex insert(AVLVertex currVert, TeamScore v) {

        if (currVert == null) {
            currVert = new AVLVertex(v);
        } else {
            int comp = currVert.value.compareTo(v);
            if (comp < 0) { // currVert has lower or equal ordering than v
                AVLVertex newRight = insert(currVert.right, v);
                currVert.right = newRight;
            } else if (comp > 0) {
                AVLVertex newLeft = insert(currVert.left, v);
                currVert.left = newLeft;
            }
        }

        currVert.updateHeight();
        currVert.updateSize();

        return balance(currVert);
    }

    public boolean search(TeamScore v) {
        return search(root, v);
    }

    public boolean search(AVLVertex currVert, TeamScore v) {

        if (currVert == null) {
            return false;
        } else {
            int comp = currVert.value.compareTo(v);
            if (comp < 0) { // value is to the right of vertex
                return search(currVert.right, v);
            } else if (comp > 0) {
                return search(currVert.left, v);
            } else {
                return true;
            }
        }
    }

    public void delete(TeamScore value) {
        root = delete(root, value);
        
    }

    public AVLVertex delete(AVLVertex currVert, TeamScore v) {
        if (currVert == null) {
            return currVert;
        } else {
            int comp = currVert.value.compareTo(v);
            if (comp < 0) {
                AVLVertex newRight = delete(currVert.right, v);
                currVert.right = newRight;
            } else if (comp > 0) {
                AVLVertex newLeft = delete(currVert.left, v);
                currVert.left = newLeft;
            } else {
                
                if (currVert.left == null && currVert.right == null) {
                    return null;
                } else if (currVert.left != null) {
                    return currVert.left;
                } else if (currVert.right != null) {
                    return currVert.right;
                } else {
                    System.out.printf("two child %s",currVert.value);
                    AVLVertex successor = currVert.right.findMin();
                    currVert.value = successor.value;
                    AVLVertex newRight = delete(currVert.right, successor.value);
                    currVert.right = newRight;
                }

            }
        }

        currVert.updateHeight();
        currVert.updateSize();

        return balance(currVert);
    }

    public int rank(TeamScore v) {
        return rank(root, v);
    }

    public int rank(AVLVertex currVert, TeamScore v) {
        if (currVert == null) {
            return -1;
        } else {
            int comp = currVert.value.compareTo(v);
            if (comp == 0) {
                int leftSize = currVert.left == null ? 0 : currVert.left.size;
                return leftSize + 1;
            } else if (comp < 0) { //currVert has lower ordering than v
                int leftSize = currVert.left == null ? 0 : currVert.left.size;
                return leftSize + 1 + rank(currVert.right, v);
            } else {
                return rank(currVert.left, v);
            }
        }
    }

    public AVLVertex balance(AVLVertex currVert) {
        int bf = currVert.bf();
        int leftBF = currVert.left == null ? 0 : currVert.left.bf();
        int rightBF = currVert.right == null ? 0 : currVert.right.bf();
        if (bf >= 2) {
            if (leftBF < 0) {
                currVert.left = rotateLeft(currVert.left);
            }
            currVert = rotateRight(currVert);
        }
        if (bf <= -2) {
            if (rightBF > 0) {
                currVert.right = rotateRight(currVert.right);
            }
            currVert = rotateLeft(currVert);
        }
        // System.out.println(this);
        return currVert;
    }

    public AVLVertex rotateLeft(AVLVertex curVertex) {
        AVLVertex w = curVertex.right;
        curVertex.right = w.left;
        w.left = curVertex;

        curVertex.updateHeight();
        w.updateHeight();

        curVertex.updateSize();
        w.updateSize();

        return w;
    }

    public AVLVertex rotateRight(AVLVertex curVertex) {
        AVLVertex w = curVertex.left;
        curVertex.left = w.right;
        w.right = curVertex;

        curVertex.updateHeight();
        w.updateHeight();

        curVertex.updateSize();
        w.updateSize();

        return w;
    }

    public void inOrder() {
        inOrder(root);
    }

    public void inOrder(AVLVertex vertex) {
        if (vertex == null)
            return;
        inOrder(vertex.left);
        System.out.printf("%s", vertex.value);
        inOrder(vertex.right);

    }

    public void preOrder() {
        preOrder(root);
    }

    public void preOrder(AVLVertex vertex) {
        if (vertex == null)
            return;

        System.out.printf("%s", vertex.value);    
        preOrder(vertex.left);
        preOrder(vertex.right);

    }

}