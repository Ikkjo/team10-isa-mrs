<template>
    <div>
        <BusinessClientNavBar id="nav"/>
        <div class="content">
            <div class="btn-group-calendar">
                <button class="btn earnings-view"
                    :class="{'not-selected': !earningsView}"
                    @click="earningsViewClicked()"
                    >Earnings
                </button>
                <button class="btn reservations-view"
                    :class="{'not-selected': !reservationsView}"
                    @click="reservationsViewClicked()"
                    >Reservations
                </button>
                <button class="btn avg-price-view"
                    :class="{'not-selected': !avgPriceView}"
                    @click="avgPriceViewClicked()"
                    >Avg. Price
                </button>
            </div>
            <div>
               <BusinessClientEarningsReport v-if="earningsView"/> 
            </div>
        </div>
    </div>
</template>

<script>
import BusinessClientNavBar from '@/components/BusinessClientNavBar.vue'
import BusinessClientEarningsReport from '@/components/BusinessClientEarningsReport.vue'
export default {
    name: 'StatisticsView',
    components: {
        BusinessClientNavBar,
        BusinessClientEarningsReport,
    },
    data() {
        return {
            earningsView: true,
            reservationsView: false,
            avgPriceView: false,
        }
    },
    methods: {
        earningsViewClicked() {
            this.earningsView = true
            this.reservationsView = false;
            this.avgPriceView = false;
        },
        reservationsViewClicked() {
            this.earningsView = false
            this.reservationsView = true;
            this.avgPriceView = false;
        },
        avgPriceViewClicked() {
            this.earningsView = false
            this.reservationsView = false;
            this.avgPriceView = true;
        }
    }
   

}
</script>

<style scoped>
.content {
  margin-top: 70px;
  padding: 0 5%;
}

.earnings-view {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
}
.reservations-view {
    border-radius:  0;
}

.avg-price-view {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
}

</style>