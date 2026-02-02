<template>
  <div id="basicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>
      <a-layout>
        <GlobalSider class="sider"/>
        <a-layout-content class="content">
          <router-view />
        </a-layout-content>
      </a-layout>
      <a-layout-footer class="footer">
        <a href="https://www.douyin.com/user/self?from_tab_name=main&showSubTab=compilation&showTab=favorite_collection" target="_blank">
          有问题找老胡
        </a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts">

import GlobalHeader from "@/components/GlobalHeader.vue";
import GlobalSider from "@/components/GlobalSider.vue";
</script>

<style scoped>
#basicLayout .header{
  padding-inline: 20px;
  background: white;
  color: unset;
  padding-bottom: 1px;
}

#basicLayout .content{
  padding: 20px;
  background: linear-gradient(to right,#fefefe,#fff);
  margin-bottom: 28px;
}

#basicLayout .footer
{
  background: #efefef;
  padding: 16px;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
#basicLayout .sider {
  background: #fff;
  padding-top: 20px;
  border-right: 0.5px solid #eee;
}

#basicLayout :deep(.ant-menu-root) {
  border-bottom: none !important;
  border-inline-end: none !important;
}

</style>
