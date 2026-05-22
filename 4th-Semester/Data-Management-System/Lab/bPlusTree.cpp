#include <iostream>
#include <vector>
using namespace std;

class Node {
public:
    bool leaf;
    vector<int> keys;
    vector<Node*> child;

    Node(bool isLeaf) {
        leaf = isLeaf;
    }
};

class BPlusTree {
private:
    Node* root;
    int MAX;

    void insertInternal(int x, Node* cursor, Node* childNode) {
        if (cursor->keys.size() < MAX) {
            int i = 0;

            while (i < cursor->keys.size() && x > cursor->keys[i])
                i++;

            cursor->keys.insert(cursor->keys.begin() + i, x);
            cursor->child.insert(cursor->child.begin() + i + 1, childNode);
        }
        else {
            Node* newInternal = new Node(false);

            vector<int> virtualKeys = cursor->keys;
            vector<Node*> virtualChild = cursor->child;

            int i = 0;
            while (i < virtualKeys.size() && x > virtualKeys[i])
                i++;

            virtualKeys.insert(virtualKeys.begin() + i, x);
            virtualChild.insert(virtualChild.begin() + i + 1, childNode);

            int split = (MAX + 1) / 2;

            cursor->keys.clear();
            cursor->child.clear();

            for (int j = 0; j < split; j++) {
                cursor->keys.push_back(virtualKeys[j]);
            }

            for (int j = 0; j < split + 1; j++) {
                cursor->child.push_back(virtualChild[j]);
            }

            int upKey = virtualKeys[split];

            for (int j = split + 1; j < virtualKeys.size(); j++) {
                newInternal->keys.push_back(virtualKeys[j]);
            }

            for (int j = split + 1; j < virtualChild.size(); j++) {
                newInternal->child.push_back(virtualChild[j]);
            }

            if (cursor == root) {
                Node* newRoot = new Node(false);

                newRoot->keys.push_back(upKey);
                newRoot->child.push_back(cursor);
                newRoot->child.push_back(newInternal);

                root = newRoot;
            }
            else {
                insertParent(root, cursor, upKey, newInternal);
            }
        }
    }

    void insertParent(Node* current, Node* child, int x, Node* newChild) {
        if (current->leaf || current->child[0]->leaf)
            return;

        for (int i = 0; i < current->child.size(); i++) {
            if (current->child[i] == child) {
                insertInternal(x, current, newChild);
                return;
            }
            else {
                insertParent(current->child[i], child, x, newChild);
            }
        }
    }

public:
    BPlusTree(int maxKeys) {
        root = nullptr;
        MAX = maxKeys;
    }

    void insert(int x) {

        if (root == nullptr) {
            root = new Node(true);
            root->keys.push_back(x);

            cout << "Inserted " << x << " as root node\n";
        }

        else {
            Node* cursor = root;
            Node* parent = nullptr;

            while (!cursor->leaf) {
                parent = cursor;

                int i = 0;
                while (i < cursor->keys.size() && x >= cursor->keys[i])
                    i++;

                cursor = cursor->child[i];
            }

            if (cursor->keys.size() < MAX) {

                int i = 0;
                while (i < cursor->keys.size() && x > cursor->keys[i])
                    i++;

                cursor->keys.insert(cursor->keys.begin() + i, x);

                cout << "Inserted " << x << " into leaf node\n";
            }

            else {
                Node* newLeaf = new Node(true);

                vector<int> tempKeys = cursor->keys;

                tempKeys.push_back(x);
                sort(tempKeys.begin(), tempKeys.end());

                cursor->keys.clear();

                int split = (MAX + 1) / 2;

                for (int i = 0; i < split; i++) {
                    cursor->keys.push_back(tempKeys[i]);
                }

                for (int i = split; i < tempKeys.size(); i++) {
                    newLeaf->keys.push_back(tempKeys[i]);
                }

                int newKey = newLeaf->keys[0];

                if (cursor == root) {
                    Node* newRoot = new Node(false);

                    newRoot->keys.push_back(newKey);
                    newRoot->child.push_back(cursor);
                    newRoot->child.push_back(newLeaf);

                    root = newRoot;
                }
                else {
                    insertInternal(newKey, parent, newLeaf);
                }

                cout << "Leaf node split after inserting " << x << "\n";
            }
        }
    }

    void display(Node* cursor) {
        if (cursor == nullptr)
            return;

        cout << "[ ";

        for (int i = 0; i < cursor->keys.size(); i++) {
            cout << cursor->keys[i] << " ";
        }

        cout << "]";

        if (!cursor->leaf) {
            cout << " -> ";

            for (int i = 0; i < cursor->child.size(); i++) {
                display(cursor->child[i]);
            }
        }
    }

    void showTree() {
        cout << "B+ Tree Structure:\n";
        display(root);
        cout << "\n";
    }
};

int main() {

    int order = 3;
    BPlusTree tree(order);

    vector<int> values = {1,2,3,4,5,6,7,8,9,10};

    cout << "===== B+ Tree Insertion =====\n";
    cout << "Order: " << order + 1 << endl; 

    for (int val : values) {
        tree.insert(val);
        tree.showTree();
        cout << "-----------------------------\nPress Enter\n";
        getchar();
    }

    return 0;
}