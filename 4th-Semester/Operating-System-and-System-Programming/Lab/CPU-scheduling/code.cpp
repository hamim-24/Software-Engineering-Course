#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <algorithm>
#include <iomanip>

using namespace std;

struct Process {
    int id;
    int arrival;
    int burst;
    int priority;
    int deadline;

    int completion = 0;
    int turnaround = 0;
    int waiting = 0;
    int response = -1;

    int remaining;
};

struct Result {
    vector<Process> processes;
    vector<int> gantt;
    double avgWaiting;
    double avgTurnaround;
};

void calculateMetrics(vector<Process>& p) {
    double totalWaiting = 0;
    double totalTurnaround = 0;

    for (auto& x : p) {
        x.turnaround = x.completion - x.arrival;
        x.waiting = x.turnaround - x.burst;
        totalWaiting += x.waiting;
        totalTurnaround += x.turnaround;
    }
}

void printResult(string name, const Result& result) {
    cout << "\n\n========================================\n";
    cout << name << "\n";
    cout << "========================================\n";

    cout << "\nGantt Chart:\n0 ";
    if (!result.gantt.empty()) {
        int currentId = result.gantt[0];
        for (size_t i = 1; i < result.gantt.size(); i++) {
            if (result.gantt[i] != currentId) {
                if (currentId == -1) cout << "| IDLE | " << i << " ";
                else cout << "| P" << currentId << " | " << i << " ";
                currentId = result.gantt[i];
            }
        }
        if (currentId == -1) cout << "| IDLE | " << result.gantt.size() << "\n";
        else cout << "| P" << currentId << " | " << result.gantt.size() << "\n";
    } else {
        cout << "\n";
    }

    cout << "\nProcess\tAT\tBT\tPriority\tDeadline\tCT\tTAT\tWT\tRT\n";

    double totalWT = 0;
    double totalTAT = 0;
    for (const auto& p : result.processes) {
        cout << "P" << p.id << "\t"
             << p.arrival << "\t"
             << p.burst << "\t"
             << p.priority << "\t\t"
             << p.deadline << "\t\t"
             << p.completion << "\t"
             << p.turnaround << "\t"
             << p.waiting << "\t"
             << p.response << "\n";
        totalWT += p.waiting;
        totalTAT += p.turnaround;
    }

    cout << fixed << setprecision(2);
    cout << "\nAverage Waiting Time    = " << totalWT / result.processes.size() << "\n";
    cout << "Average Turnaround Time = " << totalTAT / result.processes.size() << "\n";
}

Result FCFS(const vector<Process>& input) {
    vector<Process> p = input;
    sort(p.begin(), p.end(), [](const Process& a, const Process& b) {
        if (a.arrival == b.arrival) return a.id < b.id;
        return a.arrival < b.arrival;
    });

    int time = 0;
    vector<int> gantt;

    for (auto& x : p) {
        while (time < x.arrival) {
            gantt.push_back(-1);
            time++;
        }
        x.response = time - x.arrival;
        for (int i = 0; i < x.burst; i++) {
            gantt.push_back(x.id);
            time++;
        }
        x.completion = time;
    }

    calculateMetrics(p);
    sort(p.begin(), p.end(), [](const Process& a, const Process& b) {
        return a.id < b.id;
    });
    return {p, gantt, 0, 0};
}

Result SJF(const vector<Process>& input) {
    vector<Process> p = input;
    int n = p.size();
    int completed = 0;
    int time = 0;
    vector<bool> done(n, false);
    vector<int> gantt;

    while (completed < n) {
        int selected = -1;
        for (int i = 0; i < n; i++) {
            if (!done[i] && p[i].arrival <= time) {
                if (selected == -1 || 
                    p[i].burst < p[selected].burst ||
                    (p[i].burst == p[selected].burst && p[i].arrival < p[selected].arrival) ||
                    (p[i].burst == p[selected].burst && p[i].arrival == p[selected].arrival && p[i].id < p[selected].id)) {
                    selected = i;
                }
            }
        }
        
        if (selected == -1) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        p[selected].response = time - p[selected].arrival;
        for (int i = 0; i < p[selected].burst; i++) {
            gantt.push_back(p[selected].id);
            time++;
        }
        p[selected].completion = time;
        done[selected] = true;
        completed++;
    }

    calculateMetrics(p);
    return {p, gantt, 0, 0};
}

Result SRTF(const vector<Process>& input) {
    vector<Process> p = input;
    int n = p.size();
    for (auto& x : p) x.remaining = x.burst;
    
    int completed = 0;
    int time = 0;
    vector<int> gantt;

    while (completed < n) {
        int selected = -1;
        for (int i = 0; i < n; i++) {
            if (p[i].arrival <= time && p[i].remaining > 0) {
                if (selected == -1 || 
                    p[i].remaining < p[selected].remaining ||
                    (p[i].remaining == p[selected].remaining && p[i].arrival < p[selected].arrival) ||
                    (p[i].remaining == p[selected].remaining && p[i].arrival == p[selected].arrival && p[i].id < p[selected].id)) {
                    selected = i;
                }
            }
        }

        if (selected == -1) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        if (p[selected].response == -1) {
            p[selected].response = time - p[selected].arrival;
        }

        gantt.push_back(p[selected].id);
        p[selected].remaining--;
        time++;

        if (p[selected].remaining == 0) {
            p[selected].completion = time;
            completed++;
        }
    }

    calculateMetrics(p);
    return {p, gantt, 0, 0};
}

Result PriorityNP(const vector<Process>& input) {
    vector<Process> p = input;
    int n = p.size();
    int completed = 0;
    int time = 0;
    vector<bool> done(n, false);
    vector<int> gantt;

    while (completed < n) {
        int selected = -1;
        for (int i = 0; i < n; i++) {
            if (!done[i] && p[i].arrival <= time) {
                if (selected == -1 || 
                    p[i].priority < p[selected].priority ||
                    (p[i].priority == p[selected].priority && p[i].arrival < p[selected].arrival) ||
                    (p[i].priority == p[selected].priority && p[i].arrival == p[selected].arrival && p[i].id < p[selected].id)) {
                    selected = i;
                }
            }
        }

        if (selected == -1) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        p[selected].response = time - p[selected].arrival;
        for (int i = 0; i < p[selected].burst; i++) {
            gantt.push_back(p[selected].id);
            time++;
        }
        p[selected].completion = time;
        done[selected] = true;
        completed++;
    }

    calculateMetrics(p);
    return {p, gantt, 0, 0};
}

Result PriorityP(const vector<Process>& input) {
    vector<Process> p = input;
    int n = p.size();
    for (auto& x : p) x.remaining = x.burst;

    int completed = 0;
    int time = 0;
    vector<int> gantt;

    while (completed < n) {
        int selected = -1;
        for (int i = 0; i < n; i++) {
            if (p[i].arrival <= time && p[i].remaining > 0) {
                if (selected == -1 || 
                    p[i].priority < p[selected].priority ||
                    (p[i].priority == p[selected].priority && p[i].arrival < p[selected].arrival) ||
                    (p[i].priority == p[selected].priority && p[i].arrival == p[selected].arrival && p[i].id < p[selected].id)) {
                    selected = i;
                }
            }
        }

        if (selected == -1) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        if (p[selected].response == -1) {
            p[selected].response = time - p[selected].arrival;
        }

        gantt.push_back(p[selected].id);
        p[selected].remaining--;
        time++;

        if (p[selected].remaining == 0) {
            p[selected].completion = time;
            completed++;
        }
    }

    calculateMetrics(p);
    return {p, gantt, 0, 0};
}

Result RoundRobin(const vector<Process>& input, int quantum) {
    vector<Process> p = input;
    sort(p.begin(), p.end(), [](const Process& a, const Process& b) {
        if (a.arrival == b.arrival) return a.id < b.id;
        return a.arrival < b.arrival;
    });

    int n = p.size();
    for (auto& x : p) x.remaining = x.burst;

    vector<int> gantt;
    queue<int> ready;
    vector<bool> added(n, false);

    int time = 0;
    int completed = 0;

    while (completed < n) {
        for (int i = 0; i < n; i++) {
            if (!added[i] && p[i].arrival <= time) {
                ready.push(i);
                added[i] = true;
            }
        }

        if (ready.empty()) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        int current = ready.front();
        ready.pop();

        if (p[current].response == -1) {
            p[current].response = time - p[current].arrival;
        }

        int execution = min(quantum, p[current].remaining);
        for (int i = 0; i < execution; i++) {
            gantt.push_back(p[current].id);
            p[current].remaining--;
            time++;
            
            for (int j = 0; j < n; j++) {
                if (!added[j] && p[j].arrival <= time) {
                    ready.push(j);
                    added[j] = true;
                }
            }
            if (p[current].remaining == 0) break;
        }

        if (p[current].remaining == 0) {
            p[current].completion = time;
            completed++;
        } else {
            ready.push(current);
        }
    }

    calculateMetrics(p);
    sort(p.begin(), p.end(), [](const Process& a, const Process& b) {
        return a.id < b.id;
    });
    return {p, gantt, 0, 0};
}

Result EDF_NP(const vector<Process>& input) {
    vector<Process> p = input;
    int n = p.size();
    int completed = 0;
    int time = 0;
    vector<bool> done(n, false);
    vector<int> gantt;

    while (completed < n) {
        int selected = -1;
        for (int i = 0; i < n; i++) {
            if (!done[i] && p[i].arrival <= time) {
                if (selected == -1 || 
                    p[i].deadline < p[selected].deadline ||
                    (p[i].deadline == p[selected].deadline && p[i].arrival < p[selected].arrival) ||
                    (p[i].deadline == p[selected].deadline && p[i].arrival == p[selected].arrival && p[i].id < p[selected].id)) {
                    selected = i;
                }
            }
        }

        if (selected == -1) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        p[selected].response = time - p[selected].arrival;
        for (int i = 0; i < p[selected].burst; i++) {
            gantt.push_back(p[selected].id);
            time++;
        }
        p[selected].completion = time;
        done[selected] = true;
        completed++;
    }

    calculateMetrics(p);
    return {p, gantt, 0, 0};
}

Result EDF_P(const vector<Process>& input) {
    vector<Process> p = input;
    int n = p.size();
    for (auto& x : p) x.remaining = x.burst;

    int completed = 0;
    int time = 0;
    vector<int> gantt;

    while (completed < n) {
        int selected = -1;
        for (int i = 0; i < n; i++) {
            if (p[i].arrival <= time && p[i].remaining > 0) {
                if (selected == -1 || 
                    p[i].deadline < p[selected].deadline ||
                    (p[i].deadline == p[selected].deadline && p[i].arrival < p[selected].arrival) ||
                    (p[i].deadline == p[selected].deadline && p[i].arrival == p[selected].arrival && p[i].id < p[selected].id)) {
                    selected = i;
                }
            }
        }

        if (selected == -1) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        if (p[selected].response == -1) {
            p[selected].response = time - p[selected].arrival;
        }

        gantt.push_back(p[selected].id);
        p[selected].remaining--;
        time++;

        if (p[selected].remaining == 0) {
            p[selected].completion = time;
            completed++;
        }
    }

    calculateMetrics(p);
    return {p, gantt, 0, 0};
}

Result RMS_NP(const vector<Process>& input) {
    vector<Process> p = input;
    int n = p.size();
    int completed = 0;
    int time = 0;
    vector<bool> done(n, false);
    vector<int> gantt;

    while (completed < n) {
        int selected = -1;
        for (int i = 0; i < n; i++) {
            if (!done[i] && p[i].arrival <= time) {
                if (selected == -1) {
                    selected = i;
                } else {
                    int period_i = p[i].deadline - p[i].arrival;
                    int period_selected = p[selected].deadline - p[selected].arrival;
                    if (period_i < period_selected ||
                        (period_i == period_selected && p[i].arrival < p[selected].arrival) ||
                        (period_i == period_selected && p[i].arrival == p[selected].arrival && p[i].id < p[selected].id)) {
                        selected = i;
                    }
                }
            }
        }

        if (selected == -1) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        p[selected].response = time - p[selected].arrival;
        for (int i = 0; i < p[selected].burst; i++) {
            gantt.push_back(p[selected].id);
            time++;
        }
        p[selected].completion = time;
        done[selected] = true;
        completed++;
    }

    calculateMetrics(p);
    return {p, gantt, 0, 0};
}

Result RMS_P(const vector<Process>& input) {
    vector<Process> p = input;
    int n = p.size();
    for (auto& x : p) x.remaining = x.burst;

    int completed = 0;
    int time = 0;
    vector<int> gantt;

    while (completed < n) {
        int selected = -1;
        for (int i = 0; i < n; i++) {
            if (p[i].arrival <= time && p[i].remaining > 0) {
                if (selected == -1) {
                    selected = i;
                } else {
                    int period_i = p[i].deadline - p[i].arrival;
                    int period_selected = p[selected].deadline - p[selected].arrival;
                    if (period_i < period_selected ||
                        (period_i == period_selected && p[i].arrival < p[selected].arrival) ||
                        (period_i == period_selected && p[i].arrival == p[selected].arrival && p[i].id < p[selected].id)) {
                        selected = i;
                    }
                }
            }
        }

        if (selected == -1) {
            gantt.push_back(-1);
            time++;
            continue;
        }

        if (p[selected].response == -1) {
            p[selected].response = time - p[selected].arrival;
        }

        gantt.push_back(p[selected].id);
        p[selected].remaining--;
        time++;

        if (p[selected].remaining == 0) {
            p[selected].completion = time;
            completed++;
        }
    }

    calculateMetrics(p);
    return {p, gantt, 0, 0};
}

int main() {
    ifstream file("input.txt");
    if (!file) {
        cout << "Error: Could not open input.txt\n";
        return 1;
    }

    int n;
    file >> n;
    vector<Process> processes(n);

    for (int i = 0; i < n; i++) {
        file >> processes[i].id 
             >> processes[i].arrival 
             >> processes[i].burst 
             >> processes[i].priority 
             >> processes[i].deadline;
        processes[i].remaining = processes[i].burst;
    }
    file.close();

    cout << "========================================\n";
    cout << "      CPU SCHEDULING SIMULATOR\n";
    cout << "========================================\n";

    cout << "\nInput Processes:\n";
    cout << "\nID\tAT\tBT\tPriority\tDeadline\n";

    for (const auto& p : processes) {
        cout << "P" << p.id << "\t"
             << p.arrival << "\t"
             << p.burst << "\t"
             << p.priority << "\t\t"
             << p.deadline << "\n";
    }
    
    Result fcfs = FCFS(processes);
    printResult("FCFS", fcfs);

    Result sjf = SJF(processes);
    printResult("SJF - Non-Preemptive", sjf);

    Result srtf = SRTF(processes);
    //printResult("SRTF - Preemptive SJF", srtf);

    Result priorityNP = PriorityNP(processes);
    printResult("Priority - Non-Preemptive", priorityNP);

    Result priorityP = PriorityP(processes);
    //printResult("Priority - Preemptive", priorityP);

    Result rr = RoundRobin(processes, 2);
    printResult("Round Robin - Quantum = 2", rr);

    Result edfNP = EDF_NP(processes);
    printResult("EDF - Non-Preemptive", edfNP);

    Result edfP = EDF_P(processes);
    //printResult("EDF - Preemptive", edfP);

    Result rmsNP = RMS_NP(processes);
    printResult("RMS - Non-Preemptive", rmsNP);

    Result rmsP = RMS_P(processes);
    //printResult("RMS - Preemptive", rmsP);

    return 0;
}
