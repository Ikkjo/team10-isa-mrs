<template>
    <div>
        <BusinessClientNavBar id="nav"/>
        <div class="content">
        <h1>Statistics View</h1>
        <div v-if="earningsReport" class="charts">
            <div class="chart">
                <BarChart
                    :chartData="getBarChartData()"
                    tickLabel="$"
                    :plugins="getBarChartPlugins()"
                />
            </div>
            <div class="chart">
                <PieChart 
                    :chartData="getPieChartData()"/>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
import BusinessClientNavBar from '@/components/BusinessClientNavBar.vue'
import BarChart from '@/components/BarChart.vue'
import PieChart from '@/components/PieChart.vue'
import axios from 'axios'
export default {
    name: 'StatisticsView',
    components: {
      BusinessClientNavBar,
      BarChart,
      PieChart,
    },
    data() {
        return {
            earningsReport: null,
        }
    },
    methods: {
        getBarChartData() {
            let data = {
                labels: [],
                datasets: [
                    {
                        label: 'Earnings',
                        backgroundColor: '#f0a500',
                        data: []
                    }
                ]
            }
            for (let item of this.earningsReport.dailyEarnings) {
                data.labels.push(new Date(item.day).toLocaleDateString("en-US"))
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
        }
    },
    mounted() {
         axios({
                method: 'get',
                url: process.env.VUE_APP_BASE_URL+'/api/v1/business-client/report/earnings',
                params: {fromDate: 1652708658000, toDate: 1660657458000},
                headers: {
                    Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
                },
                })
                .then((response) => {
                    // console.log(response.data)
                    this.earningsReport = response.data;
                })
                .catch((error) => {
                    alert("Something went wrong")
                    console.log(error);
                }) 
    }

}
</script>

<style>
.content {
  margin-top: 70px;
}


</style>