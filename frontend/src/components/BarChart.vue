<template>
  <Bar
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :plugins="plugins"
    :css-classes="cssClasses"
    :styles="styles"
    :width="width"
    :height="height"
  />
</template>

<script>
import { Bar } from 'vue-chartjs/legacy'

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  name: 'BarChart',
  components: {
    Bar
  },
  props: {
    chartId: {
      type: String,
      default: 'bar-chart'
    },
    datasetIdKey: {
      type: String,
      default: 'label'
    },
    width: {
      type: Number,
      default: 400
    },
    height: {
      type: Number,
      default: 400
    },
    cssClasses: {
      default: '',
      type: String
    },
    styles: {
      type: Object,
      default: () => {}
    },
    plugins: {
      type: Array,
      default: () => []
    },
   
    chartData: {
        type: Object,
        default: () => ({
            labels: [
                // for day in daily earnings
                '17.06.2022.',
                '18.06.2022.',
                '19.06.2022.',
            ],
            datasets: [
                {
                    label: 'Earnings',
                    backgroundColor: '#f0a500',
                    data: [40, 20, 12] // for earnings in daily earnings
                }
            ]
        })
    },
    tickLabel: {
        type: String,
        default: "",
    },
    scales: {
      type: String,
      default: "int"
    }
  },
  data() {
    return {
        chartOptions: {
            responsive: true,
            maintainAspectRatio: false,
            scales: this.scales === 'int' ? 
              {
                y: {
                    ticks: {
                        beginAtZero: true,
                        callback: (value) => { if (Number.isInteger(value)) { return value; } },
                        stepSize: 1
                    }
                }
              } : {
                y: {
                    ticks: {
                        // Include a dollar sign in the ticks
                        callback: (value) => {
                            return this.tickLabel + value;
                        }
                    }
                }
            },
            
        },
    }
  }
}
</script>
