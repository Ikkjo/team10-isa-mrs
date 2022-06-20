<template>
    <div v-if="ratings">
        <BarChart
            :chartData="getChartData()"
            scales="10"/>
    </div>
  
</template>

<script>
import BarChart from '@/components/BarChart.vue'
import axios from 'axios'
export default {
    name: 'BusinessClientAvgRatingReport',
    components: {
        BarChart,
    },
    data() {
        return {
            ratings: null,
        }
    },
    methods: {
        getChartData() {
            let data = {
                labels: [],
                datasets: [
                    {
                        label: 'Avg. ratings',
                        backgroundColor: [],
                        data: []
                    }
                ]
            }
            for (let item of this.ratings) {
                data.labels.push(item.title)
                data.datasets[0].backgroundColor.push(this.getRandomColor())
                data.datasets[0].data.push(item.avgRating);
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
    },
    mounted() {
        axios({
            method: 'get',
            url: process.env.VUE_APP_BASE_URL+'/api/v1/business-client/report/rating',
            headers: {
                Authorization: 'Bearer ' + window.localStorage.getItem("jwt"),
            },
            })
            .then((response) => {
                if (response.data == [])
                    alert("No ratings")
                this.ratings = response.data;
            })
            .catch((error) => {
                alert("Something went wrong")
                console.log(error);
            }) 
    }

}
</script>

<style>

</style>