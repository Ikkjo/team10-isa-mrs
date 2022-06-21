<template>
    <div id="admin-earnings-report">
        <h1>Earnings Report</h1>
        <div class="date-range">
            <DatePicker
                color="yellow"
                v-model="dateRange"
                :min-date='new Date("4/1/2022")'
                :max-date='new Date()'
                :is-range='true'
                >
                <template v-slot="{ inputValue, inputEvents}">
                    <div class="form-control">
                        <div>
                            <input class="date-input" :value="inputValue.start" v-on="inputEvents.start" placeholder="From"/>
                            <span class="material-icons">arrow_right_alt</span>
                            <input class="date-input" :value="inputValue.end" v-on="inputEvents.end" placeholder="To"/>
                            <button class="btn" @click="getReport()">Generate Report</button>
                        </div>
                    </div>
                </template>
          </DatePicker>
        </div>
        <div v-if="earningsReport" class="charts">
            <div class="chart">
            <BarChart
                :chartData="chartData"
                tickLabel="$"
                scales="$"
            />
            </div>
            <div class="chart">
                <PieChart 
                    :chartData="chartData"/>
            </div>
        </div>
        
    </div>
</template>

<script scoped>
import BarChart from '@/components/BarChart.vue'
import PieChart from '@/components/PieChart.vue'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import axios from 'axios'
export default {
    name: 'AdminEarningsReport',
    components: {
        BarChart,
        PieChart,
        DatePicker
    },
    data() {
        return {
            earningsReport: null,
            dateRange: {
                start: null,
                end: null
            },
            chartData: null,
        }
    },
    methods: {
        getChartData() {
            let data = {
                labels: [],
                datasets: [
                    {
                        label: 'Earnings',
                        backgroundColor: [],
                        data: []
                    }
                ]
            }
            for (let item in this.earningsReport) {
                data.labels.push(item)
                data.datasets[0].backgroundColor.push(this.getRandomColor())
                data.datasets[0].data.push(this.earningsReport[item]);
            }
            return data;
        },
        getRandomColor() {
            let letters = '0123456789ABCDEF'.split('');
            let color = '#';
            for (let i = 0; i < 6; i++ ) {
                color += letters[Math.floor(Math.random() * 16)];
            }
            return color;
        },
        getReport() {
            if (this.dateRange.start === null || this.dateRange.end === null)
                alert('Enter date range')
            else {
                axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/admin/report',
                params: {fromDate: this.dateRange.start.getTime(), toDate: this.dateRange.end.getTime()},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    if (Object.keys(response.data).length === 0)
                        alert('No earnings in given period')
                    else {
                        this.earningsReport = response.data;
                        this.chartData = this.getChartData();
                    }

                })
                .catch((error) => {
                    alert("Something went wrong")
                    console.log(error);
                }) 
            }
            
        }
    },
}
</script>
<style scoped>
.form-control {
    width: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
}

.form-control input {
    min-width: 10px;
}

.form-control div {
    display: flex;
    align-items: center;
    padding-bottom: 50px;
}

.material-icons {
    color: grey;
}

.btn {
    height: 48px;
    margin-left: 15px;
    min-width: 165px;
}
</style>