<template>
    <div v-if="report" id="earnings-report">
        <label for="period">Report type: </label>
        <select v-model="period" name="period" id="period">
            <option value="week">Weekly</option>
            <option value="month">Monthly</option>
            <option value="year">Yearly</option>
        </select>
        <button class="btn" @click="getReport()">Generate Report</button>
        <div class="charts">
            <div class="chart">
            <BarChart
                :chartData="chartData"
            />
            </div>
            <div class="chart">
                <PieChart 
                    :chartData="chartData" />
            </div>
        </div>
        
    </div>
</template>

<script scoped>
import BarChart from '@/components/BarChart.vue'
import PieChart from '@/components/PieChart.vue'
import axios from 'axios'
export default {
    name: 'BusinessClientReservationsReport',
    components: {
        BarChart,
        PieChart,
    },
    data() {
        return {
            period: '',
            report: {},
            chartData: {},
        }
    },
    methods: {
        getChartData() {
            let data = {
                labels: [],
                datasets: [
                    {
                        label: 'Reservations',
                        backgroundColor: [],
                        data: []
                    }
                ]
            }
            let week = 1;
            for (const [key, value] of Object.entries(this.report)) {
                if (this.period === 'week')
                    data.labels.push('week ' + week++)
                else
                    data.labels.push(key)
                data.datasets[0].backgroundColor.push(this.getRandomColor())
                data.datasets[0].data.push(value);
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
            if (this.period === '')
                alert('Select report period')
            else {
                axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/business-client/report/reservations',
                params: {period: this.period},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    console.log(response);
                    if (response.data === null)
                        alert('No reservations in selected period')
                    else {
                        this.report = response.data;
                        this.chartData = this.getChartData()
                    }
                        
                    console.log(this.report)
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
    padding-top: 16px;
}

select {
    min-width: 140px;
}

.btn {
    height: 48px;
    margin-left: 15px;
    min-width: 165px;
}
</style>