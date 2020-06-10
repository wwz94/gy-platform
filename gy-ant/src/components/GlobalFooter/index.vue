<template>
  <global-footer class="footer custom-render">
    <template v-slot:links>
      <a href="#" target="_blank">国御科技</a>
      <a href="#" target="_blank">版权所有</a>
      <a href="#">Copyright &copy; 2020</a>
      <a href="#">处置平台</a>
    </template>
    <template v-slot:copyright>
      <a href="#" target="_blank">wind</a>
    </template>
  </global-footer>
</template>

<script>
import { GlobalFooter } from '@ant-design-vue/pro-layout'

export default {
  name: 'ProGlobalFooter',
  components: {
    GlobalFooter
  }
}
</script>
