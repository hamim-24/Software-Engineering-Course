#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <unordered_map>
#include <utility>
#include <queue>
#include <algorithm>
#include <functional>

using namespace std;

struct Operation {
    string t;
    char op;
    string item;
};

class PrecedenceGraph {
public:
    void addTransaction(const string& t) {
        nodes.insert(t);
    }

    void addEdge(const string& ti, const string& tj) {
        if (ti == tj) return;
        if (edges[ti].insert(tj).second) {
            nodes.insert(ti);
            nodes.insert(tj);
        }
    }

    void buildFromSchedule(const vector<Operation>& schedule) {
        unordered_map<string, vector<pair<string,char>>> lastOps;

        for (const auto& e : schedule) {
            addTransaction(e.t);
            for (auto& [prevT, prevOp] : lastOps[e.item]) {
                if (prevT == e.t) continue;
                if (prevOp == 'W' || e.op == 'W') {
                    addEdge(prevT, e.t);
                }
            }
            lastOps[e.item].push_back({e.t, e.op});
        }
    }

    bool hasCycle(vector<string>& cycle) {
        enum Color { WHITE, GRAY, BLACK };
        unordered_map<string, int> color;
        for (auto& n : nodes) color[n] = WHITE;

        vector<string> path;
        function<bool(const string&)> dfs = [&](const string& u) -> bool {
            color[u] = GRAY;
            path.push_back(u);
            for (auto& v : edges[u]) {
                if (color[v] == GRAY) {
                    auto it = find(path.begin(), path.end(), v);
                    cycle.assign(it, path.end());
                    cycle.push_back(v);
                    return true;
                }
                if (color[v] == WHITE) {
                    if (dfs(v)) return true;
                }
            }
            path.pop_back();
            color[u] = BLACK;
            return false;
        };

        for (auto& n : nodes) {
            if (color[n] == WHITE) {
                if (dfs(n)) return true;
            }
        }
        return false;
    }

    bool isConflictSerializable(vector<string>& cycle) {
        return !hasCycle(cycle);
    }

    void printGraph() const {
        for (auto& u : nodes) {
            auto it = edges.find(u);
            if (it == edges.end()) continue;
            for (auto& v : it->second) {
                cout << u << " -> " << v << "\n";
            }
        }
    }

    // Optional: topological sort -> gives an equivalent serial order
    vector<string> topoSort() {
        unordered_map<string, int> indeg;
        for (auto& n : nodes) indeg[n] = 0;
        for (auto& [u, adj] : edges)
            for (auto& v : adj) indeg[v]++;

        queue<string> q;
        for (auto& n : nodes)
            if (indeg[n] == 0) q.push(n);

        vector<string> order;
        while (!q.empty()) {
            string u = q.front(); q.pop();
            order.push_back(u);
            for (auto& v : edges[u]) {
                if (--indeg[v] == 0) q.push(v);
            }
        }
        return order; // if order.size() < nodes.size(), there's a cycle
    }

private:
    set<string> nodes;
    unordered_map<string, set<string>> edges;
};

int main() {
    vector<Operation> schedule = {
        {"T1", 'R', "A"},
        {"T2", 'W', "A"},
        {"T2", 'R', "B"},
        {"T1", 'W', "B"},
        {"T3", 'W', "A"},
    };

    PrecedenceGraph pg;
    pg.buildFromSchedule(schedule);
    pg.printGraph();

    vector<string> cycle;
    if (pg.isConflictSerializable(cycle)) {
        cout << "\nSchedule is conflict serializable.\n";
        auto order = pg.topoSort();
        cout << "Equivalent serial order: ";
        for (auto& t : order) cout << t << " ";
        cout << "\n";
    } else {
        cout << "\nSchedule is NOT conflict serializable. Cycle: ";
        for (auto& t : cycle) cout << t << " -> ";
        cout << "\n";
    }

    return 0;
}