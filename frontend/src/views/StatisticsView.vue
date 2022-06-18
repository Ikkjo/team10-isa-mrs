<template>
  <div>
      <BusinessClientNavBar id="nav"/>
      <div class="content">
        <h1>Statistics View</h1>
        <BarChart />
        <PieChart />
      </div>
  </div>
</template>

<script>
import BusinessClientNavBar from '@/components/BusinessClientNavBar.vue'
import BarChart from '@/components/BarChart.vue'
import PieChart from '@/components/PieChart.vue'
export default {
    name: 'StatisticsView',
    components: {
      BusinessClientNavBar,
      BarChart,
      PieChart,
    }

}
</script>

<style>
.content {
  margin-top: 70px;
}

</style>