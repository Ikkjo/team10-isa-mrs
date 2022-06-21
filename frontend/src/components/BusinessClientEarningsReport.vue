<template>
    <div v-if="earningsReport" id="earnings-report">
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
        <div class="charts">
            <div class="chart">
            <BarChart
                :chartData="getBarChartData()"
                tickLabel="$"
                scales="$"
            />
            </div>
            <div class="chart">
                <PieChart 
                    :chartData="getPieChartData()"/>
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
    name: 'BusinessClientEarningsReport',
    components: {
        BarChart,
        PieChart,
        DatePicker
    },
    data() {
        return {
            earningsReport: {
                fromDate: 0,
                toDate: 0,
                dailyEarnings: [],
                individualEarnings: []
            },
            dateRange: {
                start: null,
                end: null
            },
            colors: [],
        }
    },
    methods: {
        getBarChartData() {
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
            for (let item of this.earningsReport.dailyEarnings) {
                data.labels.push(new Date(item.day).toLocaleDateString("en-US"))
                data.datasets[0].backgroundColor.push(this.getRandomColor())
                data.datasets[0].data.push(item.earnings);
            }
            return data;
        },
        getPieChartData() {
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
            for (let item of this.earningsReport.individualEarnings) {
                console.log(item)
                data.labels.push(item.rentalEntityTitle)
                data.datasets[0].backgroundColor.push(this.getRandomColor())
                data.datasets[0].data.push(item.earnings);
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
                url: process.env.VUE_APP_BASE_URL+'/api/v1/business-client/report/earnings',
                params: {fromDate: this.dateRange.start.getTime(), toDate: this.dateRange.end.getTime()},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.data.individualEarnings.length === 0 || response.data.dailyEarnings.length === 0)
                        alert('No earnings in given period')
                    else
                        this.earningsReport = response.data;
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
#earnings-report {
    padding-top: 65px;
}


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
    position: absolute;
    display: flex;
    align-items: center;
    padding-bottom: 50px;
}

.date-range {
    position: relative;
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